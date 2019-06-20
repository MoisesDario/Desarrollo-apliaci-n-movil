package m.app.firebaseproductos;

import android.content.ContentValues;

public class Producto {

    private String productoid;
    private String nombreProducto;
    private String descripcion;
    private String precio;

    public Producto(){

    }

    public Producto(String productoid,String nombreProducto, String descripcion,String precio){
        this.productoid = productoid;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getProductoid() {
        return productoid;
    }

    public void setProductoid(String productoid) {
        this.productoid = productoid;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

}
