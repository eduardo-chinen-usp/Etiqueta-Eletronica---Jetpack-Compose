package br.usp.iee.chinen.digitaltagbyeduardochinen

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel


class MainActivityViewModel: ViewModel(){

    //Variáveis
    val listaModosDeOperacao = listOf<String>("Três Amostras sem Sufixo", "Três Amostras", "Uma amostra", "Uma amostra sem Sufixo")
    val listaDeSufixos = listOf<String>("", "VERSO", "FRENTE", "ANTES", "APÓS", "APÓS RETIRADA")
    var flagHabilitaSufixo by mutableStateOf(false)
    var idModoDeOperacao by mutableStateOf(0)
    var idSufixo = 0
    var txtAmostras by mutableStateOf("AMOSTRAS")
    var txtSufixo by mutableStateOf(" ")
    var amostraA by mutableStateOf(1)
    var amostraB by mutableStateOf(2)
    var amostraC by mutableStateOf(3)
    var amostraU by mutableStateOf(1)
    var txtModoDeOperacao by mutableStateOf("Modo de Operação")
    //var txtTesteA by mutableStateOf("testeA")


    //Controle da Interface Grafica

    fun modoZero(){
        txtModoDeOperacao = listaModosDeOperacao.elementAt(0)
        flagHabilitaSufixo = false
        txtAmostras = "AMOSTRAS"
        txtSufixo = " "
    }

    fun modoUm(){
        txtModoDeOperacao = listaModosDeOperacao.elementAt(1)
        idSufixo = 1
        flagHabilitaSufixo = true
        txtAmostras = "AMOSTRAS"
        txtSufixo = "FRENTE"
    }

    fun modoDois(){
        txtModoDeOperacao = listaModosDeOperacao.elementAt(2)
        idSufixo = 3
        flagHabilitaSufixo = true
        txtAmostras = "AMOSTRA"
        if(flagHabilitaSufixo){
            txtSufixo = listaDeSufixos.elementAt(idSufixo)
        }
    }

    fun modoTres(){
        txtModoDeOperacao = listaModosDeOperacao.elementAt(3)
        flagHabilitaSufixo = false
        txtAmostras = "AMOSTRA"
        txtSufixo = " "
    }

    //Lógica do programa

    fun btnSelecionarModoDeOperacao(){
        idModoDeOperacao += 1
        when (idModoDeOperacao){
            0 -> modoZero()
            1 -> modoUm()
            2 -> modoDois()
            3 -> modoTres()
            else -> {
                idModoDeOperacao = 0
                modoZero()
            }
        }
    }
    
    /*
    fun btnHabilitaSufixo(){
        flagHabilitaSufixo = !flagHabilitaSufixo
        if(!flagHabilitaSufixo){
            txtSufixo = " "
        }
    }
    */

    fun btnProximo(){
        if (idModoDeOperacao == 0){
            amostraA += 3
            amostraB += 3
            amostraC += 3
        }
        if (idModoDeOperacao == 1){
            if(idSufixo == 1){
                txtSufixo = listaDeSufixos.elementAt(idSufixo)
                idSufixo = 2
            }else{
                txtSufixo = listaDeSufixos.elementAt(idSufixo)
                idSufixo = 1
                amostraA += 3
                amostraB += 3
                amostraC += 3
            }
        }
        if(idModoDeOperacao == 2){
            if(idSufixo < 5) {
                idSufixo += 1
                txtSufixo = listaDeSufixos.elementAt(idSufixo)
            }else{
                idSufixo = 3
                amostraU += 1
                txtSufixo = listaDeSufixos.elementAt(idSufixo)
            }
        }
        if (idModoDeOperacao == 3){
            amostraU += 1
        }
    }

    fun btnAnterior(){
        if(idModoDeOperacao == 0){
            if(amostraA > 1){
                amostraA -= 3
                amostraB -= 3
                amostraC -= 3
            }else{
                amostraA = 1
                amostraB = 2
                amostraC = 3
            }
        }
        if(idModoDeOperacao == 1){
            if (idSufixo == 1){
                txtSufixo = listaDeSufixos.elementAt(idSufixo)
                idSufixo = 2
                if(amostraA > 1){
                    amostraA -= 3
                    amostraB -= 3
                    amostraC -= 3
                }else{
                    amostraA = 1
                    amostraB = 2
                    amostraC = 3
                    txtSufixo = listaDeSufixos.elementAt(idSufixo)
                    idSufixo = 1
                }
            }else{
                txtSufixo = listaDeSufixos.elementAt(idSufixo)
                idSufixo = 1
            }
        }
        if(idModoDeOperacao == 2){
            if (amostraU > 1) {
                if (idSufixo > 3) {
                    idSufixo -= 1
                    txtSufixo = listaDeSufixos.elementAt(idSufixo)
                } else {
                    idSufixo = 5
                    amostraU -= 1
                    txtSufixo = listaDeSufixos.elementAt(idSufixo)
                }
            }else{
                if (idSufixo > 3) {
                    idSufixo -= 1
                    txtSufixo = listaDeSufixos.elementAt(idSufixo)
                }
            }
        }
        if(idModoDeOperacao == 3){
            if(amostraU > 1){
                amostraU -= 1
            }else{
                amostraU = 1
            }
        }
    }

    fun btnReset(){
        flagHabilitaSufixo = false
        idModoDeOperacao = 0
        idSufixo = 0
        txtAmostras = "AMOSTRAS"
        txtSufixo = " "
        amostraA = 1
        amostraB = 2
        amostraC = 3
        amostraU = 1
    }
}