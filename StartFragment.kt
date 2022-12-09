package com.example.todolist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolist.databinding.FragmentInicioBinding
import com.squareup.picasso.Picasso


class StartFragment : Fragment() {

    private var _binding : FragmentInicioBinding? = null
    private val binding get() = _binding!!

    //2.Creamos una referencia a la interfaz, y le asignamos el valor de nulo.
    var listener : LoginListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //3.Dentro del ciclo de vida(onAttach) le pasamos el contexto a la referencia, le decimos que es un LoginListener.
        listener = context as LoginListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInicioBinding.inflate(inflater, container, false)

        //Cargamos la imagen desde internet, y la colocamos dentro del imageView, con la libreria Picasso.
        Picasso.get().load("https://geekflare.com/wp-content/uploads/2022/02/to-do-list.png").into(binding.ivPrincipal)

        //4.Cuando presionamos el boton de crear cuenta, llamamos al metodo dentro del listener(informamos que este mismo no va a ser nulo "!!"). En este es caso createBill()
        binding.btnCrearCuenta.setOnClickListener { listener!!.createBill() }
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //1.Creamos una interfaz, para comunicarnos con otro fragment.
    interface LoginListener{
        fun createBill()
    }

}