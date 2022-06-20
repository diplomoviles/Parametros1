package com.tallercmovil.parametros1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.fragment.findNavController
import com.tallercmovil.parametros1.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)


        parentFragmentManager.setFragmentResultListener("datosf2", this, FragmentResultListener { requestKey, result ->
            val apellido = result.getString("apellido", "")

            Toast.makeText(context, "Datos recibidos del fragment 2: $apellido", Toast.LENGTH_LONG).show()

        })

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("nombre", "Amaury")
            bundle.putParcelable("alumno", Alumno("1","Héctor", "Matsumura"))

            parentFragmentManager.setFragmentResult("datosf1",bundle)

            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}