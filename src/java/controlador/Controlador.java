package controlador;

import hibernate.Fotos;
import modelo.ModeloFoto;
import modelo.ModeloInmueble;
import hibernate.Inmueble;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Aarón
 */
@WebServlet(name = "Controlador", urlPatterns = {"/control"})
public class Controlador extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destino = "index.html";
        boolean forward = false;
        String target, op, action, view;

        //..
        target = request.getParameter("target");
        op = request.getParameter("op");
        action = request.getParameter("action");
        System.out.println(target + " " + op + " " + action);
        
        if (target.equals("inmueble") && op.equals("select") && action.equals("view")) {
            forward = true;
            destino = "WEB-INF/inmueble/index.jsp";
            request.setAttribute("datos", ModeloInmueble.get());
        } else {
            if (target.equals("inmueble") && op.equals("delete") && action.equals("op")) {
                forward = false;
                ModeloInmueble.delete(request.getParameter("id"));
                destino = "control?target=inmueble&op=select&action=view";
            } else {
                if (target.equals("inmueble") && op.equals("insert") && action.equals("view")) {
                    forward = true;
                    destino = "WEB-INF/inmueble/insert.jsp";
                } else {
                    if (target.equals("inmueble") && op.equals("insert") && action.equals("op")) {
                        forward = false;
                        destino = "control?target=inmueble&op=select&action=view";
                        Inmueble inmueble = new Inmueble();
                        inmueble.setDireccion(request.getParameter("direccion"));
                        inmueble.setLocalidad(request.getParameter("localidad"));
                        inmueble.setTipo(request.getParameter("tipo"));
                        inmueble.setPrecio(Double.parseDouble(request.getParameter("tipo")));
                        inmueble.setUsuario("Aaron");
                        Calendar c=Calendar.getInstance();
                        inmueble.setFechaalta(c.getTime());
                        ModeloInmueble.insert(inmueble);
                    } else {
                        if (target.equals("inmueble") && op.equals("edit") && action.equals("view")) {
                            forward = true;
                            request.setAttribute("inmueble", ModeloInmueble.get(request.getParameter("id")));
                            destino = "WEB-INF/inmueble/edit.jsp";
                        } else {
                            if (target.equals("inmueble") && op.equals("edit") && action.equals("op")) {
                                forward = false;
                                destino = "control?target=inmueble&op=select&action=view";
                                Inmueble inmueble = new Inmueble();
                                inmueble.setDireccion(request.getParameter("direccion"));
                                inmueble.setLocalidad(request.getParameter("localidad"));
                                inmueble.setTipo(request.getParameter("tipo"));
                                inmueble.setUsuario(request.getParameter("usuario"));
                                inmueble.setFechaalta(Date.valueOf(request.getParameter("fecha")));
                                ModeloInmueble.update(inmueble);
                            } else {
                                if (target.equals("inmueble") && op.equals("viewfoto") && action.equals("view")) {
                                    forward = true;
                                    request.setAttribute("inmueble", ModeloFoto.get(request.getParameter("id")));
                                    destino = "WEB-INF/inmueble/viewfoto.jsp";
                                } else {
                                    if (target.equals("inmueble") && op.equals("addfoto") && action.equals("view")) {
                                        forward = true;
                                        request.setAttribute("inmueble", ModeloFoto.get(request.getParameter("id")));
                                        destino = "WEB-INF/inmueble/addfoto.jsp";
                                    }else {
                                    if (target.equals("inmueble") && op.equals("addfoto") && action.equals("op")) {
                                        forward = false;
                                        boolean error = false;
                                        String id = request.getParameter("id");
                                        response.setContentType("text/html;charset=UTF-8");
                                        Part archivoPost = request.getPart("archivo");
                                        String nombre = "inmueble_" + id;
                                        String ruta = getServletContext().getRealPath("/") + "imagenes/"+ archivoPost;
                                        InputStream input = archivoPost.getInputStream();
                                        try {
                                            OutputStream out = new FileOutputStream(ruta + nombre + ".jpg");
                                            byte[] b = new byte[2048];
                                            int length;
                                            while ((length = input.read(b)) != -1) {
                                                out.write(b, 0, length);
                                            }
                                        } catch (Exception e) {
                                            error = true;
                                        } finally {
                                            input.close();
                                        }

                                        Fotos foto = new Fotos();
                                        foto.setIdInmueble(Integer.valueOf(id));
                                        foto.setUrl(nombre + ".jpg");
                                        ModeloFoto.insert(foto);
                                        destino = "control?target=inmueble&op=select&action=view";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (forward) {
            request.getRequestDispatcher(destino).forward(request, response);
        } else {
            response.sendRedirect(destino);
        }
    }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
