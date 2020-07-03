package br.com.jsfinicio.repository;

import br.com.jsfinicio.model.EstadoModel;
import br.com.jsfinicio.util.Conexao;
import java.util.ArrayList;
import java.util.List;

public class EstadoRepository extends Conexao{
    public List<EstadoModel> buscarTodos(){
        List<EstadoModel> listaDeEstados = new ArrayList<>();
        super.inicializa();
        listaDeEstados = super.getSess().createQuery("from EstadoModel").list();
        super.executar();
        return listaDeEstados;
    }
    
    public EstadoModel buscarPorID(int estadoId){
        EstadoModel estado = new EstadoModel();
        super.inicializa();
        estado = (EstadoModel) super.getSess().get(EstadoModel.class, estadoId);
        super.executar();
        return estado;
    }
}
