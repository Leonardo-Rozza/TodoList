package com.example.todolist


import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.getSystemService
import com.example.todolist.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    //2.Creamos una referencia a la interfaz, y le asignamos el valor de nulo.
    var listener : TaskListener? = null

    private var user = "profe"
    private var password = "1234"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //3.Dentro del ciclo de vida(onAttach) le pasamos el contexto a la referencia, le decimos que es un WorksListener.
        listener = context as TaskListener
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        //Cuando le demos click a ingresar, llama a la funcion validation, si los datos son correctos, pasará a la siguiente activity.
        binding.btnLogin.setOnClickListener { validation()}
        return binding.root
    }

    //4.Creamos un método, donde trabajamos en el listener, este mismo puede ser nulo, a travez del operador LET nos permite trabajar con el objeto de la funcion.(este mismo funciona como un condicional, si es nulo no hace nada, caso contrario accede a sus metodos o atributos).
    //  Le pasamos el mensaje que recibe como parametro nuestro sendWorks, en este caso el texto que se almacene dentro del casillero de usuario.
    private fun sendMessage(){
        listener?.let {
            it.sendTask(binding.etUser.text.toString())
        }
    }


    //Cuando se destruya, le asignamos nuevamente un valor nulo al binding, para que no quede asociado a ninguna vista.
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Validamos que el usuario y la contraseña sean correctas, obteniendo el valor a traves del binding. y comparandolos con el método equals.
    private fun validation(){
        if(binding.etUser.text.toString().lowercase().equals(user) && binding.etPassword.text.toString().equals(password)){
            Toast.makeText(context, "Bienvenido $user!!", Toast.LENGTH_LONG).show()
            //5.Cuando el usuario pase la validacion, llamamos al método anteriormente creado que envia el mensaje.
            sendMessage()
        } else{
            Toast.makeText(context, "Usuario y/o contraseña incorectos", Toast.LENGTH_LONG).show()
        }
        emptyLockers()
        hideKeyboard()
    }

    //Vaciamos casilleros y colocamos el focus del cursor en el casillero del usuario
    private fun emptyLockers(){
        binding.etUser.setText("")
        binding.etPassword.setText("")
        binding.etUser.requestFocus()
    }

    //Ocultamos el teclado
    private fun hideKeyboard(){
        val imm : InputMethodManager = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etPassword.windowToken, 0)
    }

    //1.Creamos una interfaz en este caso con un método. Para poder comunicarnos con las activitys, u otros fragments.
    interface TaskListener{
        fun sendTask(msg:String)
    }
}