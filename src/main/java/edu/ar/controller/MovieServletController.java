package edu.ar.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ar.data.PeliculaDAO;
import edu.ar.model.Pelicula;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/movies")
public class MovieServletController extends HttpServlet {

  static Logger logger = LoggerFactory.getLogger(MovieServletController.class);
  List<Pelicula> movieList = new ArrayList<>();
  ObjectMapper mapper = new ObjectMapper();
  


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String route = req.getParameter("action");
    logger.info("route : " + route);
    switch (route) {
      case "getAll"-> {
        res.setContentType("application/json; charset=UTF-8");
        movieList = PeliculaDAO.obtener();
        logger.info("Dentro de getAll : " + movieList.size());
        //transformo en json y lo envio
        mapper.writeValue(res.getWriter(), movieList);
        logger.info(mapper.toString());
      }
      
      default -> {
        System.out.println("Parámetro no válido");
      }
    }
  }

  
  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Configurar cabeceras CORS
        resp.setHeader("Access-Control-Allow-Origin", "*"); // Permitir acceso desde cualquier origen
        resp.setHeader("Access-Control-Allow-Methods", "*"); // Métodos permitidos
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Cabeceras permitidas
      // Establecer la codificación de caracteres
      req.setCharacterEncoding("UTF-8");
      resp.setCharacterEncoding("UTF-8");
  
      try {
          // Leer JSON del cuerpo de la solicitud y convertirlo en un objeto Pelicula
          Pelicula pelicula = mapper.readValue(req.getInputStream(), Pelicula.class);
          // levantar el id del queryParams e insertarlo en el objeto pelicula
          String idParam = req.getParameter("id");
          if (idParam != null) {
              int id = Integer.parseInt(idParam);
              pelicula.setId(id);
          }
  
          // Verificar que la película tenga un ID
          if (pelicula.getId() == 0) {
              resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID de la película es necesario para actualizar.");
              return;
          }
  
          // Actualizar la película en la base de datos
          PeliculaDAO.actualizar(pelicula);
  
          // Establecer el tipo de contenido de la respuesta a JSON
          resp.setContentType("application/json");
  
          // Escribir la respuesta JSON
          resp.getWriter().write("{\"message\": \"Pelicula actualizada exitosamente\"}");
  
          // Establecer el estado de la respuesta a 200 (OK)
          resp.setStatus(HttpServletResponse.SC_OK);
      } catch (IllegalArgumentException e) {
          resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
      } catch (Exception e) {
          resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar la película");
          e.printStackTrace();
      }
  }
  

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Configurar cabeceras CORS
        resp.setHeader("Access-Control-Allow-Origin", "*"); // Permitir acceso desde cualquier origen
        resp.setHeader("Access-Control-Allow-Methods", "*"); // Métodos permitidos
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Cabeceras permitidas
      // Establecer la codificación de caracteres
      req.setCharacterEncoding("UTF-8");
      resp.setCharacterEncoding("UTF-8");

      // Obtener el parámetro 'id' de la URL
      String idParam = req.getParameter("id");
      if (idParam != null) {
          try {
              int id = Integer.parseInt(idParam);
              PeliculaDAO.delete(id);
              
              // Establecer el tipo de contenido de la respuesta a JSON
              resp.setContentType("application/json");
              
              // Escribir la respuesta JSON
              resp.getWriter().write("{\"message\": \"Pelicula eliminada exitosamente\"}");
              
              // Establecer el estado de la respuesta a 200 (OK)
              resp.setStatus(HttpServletResponse.SC_OK);
          } catch (NumberFormatException e) {
              resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
          }
      } else {
          resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID no proporcionado");
      }
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // Configurar cabeceras CORS
    resp.setHeader("Access-Control-Allow-Origin", "*"); // Permitir acceso desde cualquier origen
    resp.setHeader("Access-Control-Allow-Methods", "*"); // Métodos permitidos
    resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // Cabeceras permitidas
   // Establecer la codificación de caracteres
   req.setCharacterEncoding("UTF-8");
   resp.setCharacterEncoding("UTF-8");
   
   // Leer JSON del cuerpo de la solicitud y convertirlo en un objeto Pelicula
   Pelicula pelicula = mapper.readValue(req.getInputStream(), Pelicula.class);
   
   // Insertar la película en la base de datos
   int id = PeliculaDAO.insertar(pelicula);
   
   // Convertir el id a json
   String jsonResponse = mapper.writeValueAsString(id);
   
   // Establecer el tipo de contenido de la respuesta a JSON
   resp.setContentType("application/json");
   
   // Escribir la respuesta JSON
   resp.getWriter().write(jsonResponse);
   
   // Establecer el estado de la respuesta a 201 (Creado)
   resp.setStatus(HttpServletResponse.SC_CREATED);
}
}
