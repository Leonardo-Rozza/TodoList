package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.todolist.databinding.ActivityMainBinding

//6.Llamamos a la interfaz.
class MainActivity : AppCompatActivity(), StartFragment.LoginListener, LoginFragment.TaskListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(StartFragment()) //Llamamos al fragment, pasando como páramentro el mismo.
    }

    private fun loadFragment(fragment:Fragment){
        val transactionFragment = supportFragmentManager.beginTransaction() //Creamos y comenzamos la transaccion
        transactionFragment.replace(binding.mainActivity.id, fragment) //Reemplazamos el fragmento dentro de la vista
        transactionFragment.addToBackStack(null) //Le decimos que con el boton de "volver", no nos saque de la aplicacion. Regresa a la pantalla anterior.
        transactionFragment.commit() //Confirmamos la transaccion
    }

    //8. El mensaje viaja a la siguiente Activity, a traves del método que las conecta.
    private fun launchTask(msg:String){
        val intent = Intent(this, MyTasks::class.java).apply { putExtra(MyTasks.KEY_MESSAGE, msg) }
        startActivity(intent)
    }

    //5.Sobreescribimos su método. LLamamos a la funcion loadFragment. Pasandole como parámetro el fragmento que queremos llamar. En este caso "LoginFragment".
    override fun createBill() {
        loadFragment(LoginFragment())
    }

    //7. Sobreescribimos su método. En este mismo llamamos a "launchTask". Pasandole como parámetro msg que es el string, que cargamos en el paso 4.
    override fun sendTask(msg: String) {
        launchTask(msg)
    }

}
