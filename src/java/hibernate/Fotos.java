package hibernate;
// Generated 02-feb-2015 19:14:03 by Hibernate Tools 4.3.1



/**
 * Fotos generated by hbm2java
 */
public class Fotos  implements java.io.Serializable {


     private Integer id;
     private Integer idInmueble;
     private String url;

    public Fotos() {
    }

    public Fotos(Integer idInmueble, String url) {
       this.idInmueble = idInmueble;
       this.url = url;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getIdInmueble() {
        return this.idInmueble;
    }
    
    public void setIdInmueble(Integer idInmueble) {
        this.idInmueble = idInmueble;
    }
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }




}


