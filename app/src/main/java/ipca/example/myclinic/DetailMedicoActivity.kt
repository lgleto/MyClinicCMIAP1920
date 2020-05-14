package ipca.example.myclinic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ipca.example.myclinic.entities.Medico
import kotlinx.android.synthetic.main.activity_detail_medico.*
import org.json.JSONObject

class DetailMedicoActivity : AppCompatActivity() {

    var medico : Medico? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_medico)

        intent.extras?.let {
            val medicoId = it.getLong("medico_id")
            VolleyHelper.instance.getMedico(this, medicoId){jsonObject ->
                jsonObject?.let {js ->
                    medico = Medico.parseJson(js.get(0) as JSONObject)
                    editTextName.setText(medico?.nome)
                    editTextAddress.setText(medico?.morada)
                }
            }
        }

        floatingActionButtonSave.setOnClickListener {
            VolleyHelper.instance.updateMedico(this@DetailMedicoActivity, medico?.id!!,  medico?.toJson()!!) {
                finish()
            }

        }
    }
}
