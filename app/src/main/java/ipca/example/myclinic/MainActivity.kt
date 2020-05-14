package ipca.example.myclinic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import ipca.example.myclinic.entities.Medico
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var medicos : MutableList<Medico> = ArrayList()

    var adapter : MedicosAdapter? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MedicosAdapter()
        listViewMedicos.adapter =  adapter

        VolleyHelper.instance.getAllMedicos(this) { response ->
            response?.let {
                Log.d("MainActivity", it.toString())
                for (index in 0 until it.length()) {
                    val jsonArticle = it[index] as JSONObject
                    medicos.add(Medico.parseJson(jsonArticle))
                }

                adapter?.notifyDataSetChanged()
            }
        }
    }

    inner class MedicosAdapter : BaseAdapter(){
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view  = layoutInflater.inflate(R.layout.row_medico,parent,false)

            val textViewNome = view.findViewById<TextView>(R.id.textViewNome)
            val textViewEspecialidade  = view.findViewById<TextView>(R.id.textViewEspecialidade)


            textViewNome.text = medicos[position].nome
            textViewEspecialidade.text  = medicos[position].id_especialidade

            view.setOnClickListener {

                val intent = Intent(this@MainActivity, DetailMedicoActivity::class.java)
                intent.putExtra("medico_id", medicos[position].id)
                startActivity(intent)

            }


            return view
        }

        override fun getItem(position: Int): Any {
            return medicos[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return medicos.size
        }

    }


}
