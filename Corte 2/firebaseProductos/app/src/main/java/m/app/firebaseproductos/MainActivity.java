package m.app.firebaseproductos;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editNombreProducto;
    EditText editDescripcion;
    EditText editPrecio;
    Button btnAddProducto;
    ListView listViewProducto;

    List<Producto> Productos;
    DatabaseReference databaseReference; //Referenciar a la base de datos de fireBase


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listadoProducto();
        agregarProducto();
    }

    private void listadoProducto(){
        databaseReference = FirebaseDatabase.getInstance().getReference("productos");

        editNombreProducto = (EditText) findViewById(R.id.editNombre);
        editDescripcion = (EditText) findViewById(R.id.editDescripcion);
        editPrecio = (EditText) findViewById(R.id.editPrecio);
        listViewProducto = (ListView) findViewById(R.id.listViewProducto);
        btnAddProducto = (Button) findViewById(R.id.btnAddProducto);

        Productos = new ArrayList<>();
    }

    public void agregarProducto(){
        btnAddProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProducto();
            }
        });

        listViewProducto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Producto Producto = Productos.get(i);
                UpdateAndDeleteDialog(Producto.getProductoid(),Producto.getNombreProducto(),Producto.getDescripcion(),Producto.getPrecio());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Productos.clear();

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Producto producto = postSnapshot.getValue(Producto.class);

                    Productos.add(producto);
                }

                ProductoList ProductoAdapter = new ProductoList(MainActivity.this, Productos);

                listViewProducto.setAdapter(ProductoAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void UpdateAndDeleteDialog(final String productoid, final String nombreProducto, final String descripcion, final String precio){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.activity_update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText updateEditNombreProducto = (EditText) dialogView.findViewById(R.id.updateditNombreProducto);
        final EditText updateEditDescripcion = (EditText) dialogView.findViewById(R.id.updateeditDescripcion);
        final EditText updateEditPrecio = (EditText) dialogView.findViewById(R.id.updateeditPrecio);

        updateEditNombreProducto.setText("Nombre del producto: " + nombreProducto);
        updateEditDescripcion.setText("Descripcón: " + descripcion);
        updateEditPrecio.setText(" $ " + precio);

        final Button btnUpdate = (Button) dialogView.findViewById(R.id.btnEditarProducto);
        final Button btnDelete = (Button) dialogView.findViewById(R.id.btnEliminarProducto);

        dialogBuilder.setTitle("ID Producto: " + productoid);

        final AlertDialog b = dialogBuilder.create();
        b.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = updateEditNombreProducto.getText().toString().trim();
                String descripcion = updateEditDescripcion.getText().toString().trim();
                String precio = updateEditPrecio.getText().toString().trim();

                if (!TextUtils.isEmpty(nombre)) {
                    if (!TextUtils.isEmpty(descripcion)) {
                        if (!TextUtils.isEmpty(precio)) {
                            updateProducto(productoid, nombre, descripcion, precio);
                            b.dismiss();
                        }
                    }
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteProducto(productoid);
                b.dismiss();
            }
        });


    }
    private boolean updateProducto(String id, String nombre, String descripcion, String precio) {
        DatabaseReference UpdateReference = FirebaseDatabase.getInstance().getReference("productos").child(id);
        Producto Producto= new Producto(id, nombre, descripcion, precio);

        UpdateReference.setValue(Producto);

        Toast.makeText(getApplicationContext(), "Producto editado", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteProducto(String id) {
        DatabaseReference DeleteReference = FirebaseDatabase.getInstance().getReference("productos").child(id);

        DeleteReference.removeValue();

        Toast.makeText(getApplicationContext(), "Producto eliminado", Toast.LENGTH_LONG).show();
        return true;
    }


    private void addProducto() {
        String nombre = editNombreProducto.getText().toString().trim();
        String descripcion = editDescripcion.getText().toString().trim();
        String precio = editPrecio.getText().toString().trim();


        if (!TextUtils.isEmpty(nombre)) {
            if (!TextUtils.isEmpty(descripcion)) {
                if (!TextUtils.isEmpty(precio)) {

                    String id = databaseReference.push().getKey();

                    Producto Producto = new Producto(id, nombre, descripcion, precio);

                    databaseReference.child(id).setValue(Producto);

                    editNombreProducto.setText("");
                    editDescripcion.setText("");
                    editPrecio.setText("");

                    Toast.makeText(this, "Producto agregado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Ingrese el precio", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Ingrese la descripcion", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Ingrese el nombre producto", Toast.LENGTH_LONG).show();
        }
    }
}

