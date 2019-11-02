package br.com.listrepositori.github.view.listener

import br.com.henriqueoliveira.desafioandroidconcrete.service.models.Repository

interface RepositoryClickListener {

    fun clickListener(repository: Repository)
}