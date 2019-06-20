package m.app.firebaseproductos;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class ProductoList extends ArrayAdapter<Producto> {

    private Activity context;

    List<Producto> Productos;

    public ProductoList(Activity context, List<Producto> Productos){
        super(context, R.layout.activity_layout_producto_list, Productos);
        this.context = context;
        this.Productos = Productos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_layout_producto_list, null, true);

        TextView txtNombre = (TextView) listViewItem.findViewById(R.id.txtNombreProducto);
        TextView txtDescripcion = (TextView) listViewItem.findViewById(R.id.txtDescripcion);
        TextView txtPrecio = (TextView) listViewItem.findViewById(R.id.txtPrecio);

        Producto Producto = Productos.get(position);

        txtNombre.setText(  Producto.getNombreProducto());

        txtDescripcion.setText(Producto.getDescripcion());

        txtPrecio.setText(Producto.getPrecio());

        return listViewItem;
    }
}
