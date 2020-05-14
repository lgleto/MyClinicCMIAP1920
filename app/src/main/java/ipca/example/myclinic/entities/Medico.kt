package ipca.example.myclinic.entities

import org.json.JSONObject

class Medico {

    var id                 : Long?   = null
    var nome               : String? = null
    var morada             : String? = null
    var cod_postal         : String? = null
    var dta_nascimento     : String? = null
    var id_especialidade   : String? = null
    var dta_ini_servico    : String? = null

    constructor(
        id: Long?,
        nome: String?,
        morada: String?,
        cod_postal: String?,
        dta_nascimento: String?,
        id_especialidade: String?,
        dta_ini_servico: String?
    ) {
        this.id = id
        this.nome = nome
        this.morada = morada
        this.cod_postal = cod_postal
        this.dta_nascimento = dta_nascimento
        this.id_especialidade = id_especialidade
        this.dta_ini_servico = dta_ini_servico
    }

    constructor(){}

    fun toJson () : JSONObject {
        val jsonObject : JSONObject = JSONObject()
        jsonObject.put("id"              , id              )
        jsonObject.put("nome"            , nome            )
        jsonObject.put("morada"          , morada          )
        jsonObject.put("cod_postal"      , cod_postal      )
        jsonObject.put("dta_nascimento"  , dta_nascimento  )
        jsonObject.put("id_especialidade", id_especialidade)
        jsonObject.put("dta_ini_servico" , dta_ini_servico )


        return  jsonObject
    }

    companion object {
        fun parseJson(jsonArticle: JSONObject) : Medico {
            val medico = Medico ()

            medico.id               = jsonArticle.getLong  ("id"               )
            medico.nome             = jsonArticle.getString("nome"             )
            medico.morada           = jsonArticle.getString("morada"           )
            medico.cod_postal       = jsonArticle.getString("cod_postal"       )
            medico.dta_nascimento   = jsonArticle.getString("dta_nascimento"   )
            medico.id_especialidade = jsonArticle.getString("id_especialidade" )
            medico.dta_ini_servico  = jsonArticle.getString("dta_ini_servico"  )

            return medico
        }
    }


}