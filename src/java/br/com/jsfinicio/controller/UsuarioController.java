package br.com.jsfinicio.controller;

import br.com.jsfinicio.model.PessoaModel;
import br.com.jsfinicio.repository.UsuarioRepository;
import br.com.jsfinicio.util.Usuario;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class UsuarioController {

    private Usuario usuario;
    private UsuarioRepository usuarioRepository;

    public UsuarioController() {
        this.usuario = new Usuario();
        this.usuarioRepository = new UsuarioRepository();
    }

    public void login() throws IOException {
        List<PessoaModel> listaDePessoas = this.usuarioRepository.buscar(this.usuario.getLogin());
        if (listaDePessoas == null || listaDePessoas.isEmpty()) {
            //avisem que não encontraram ninguém com o login x
            System.out.println(" login errado");
        } else {
            //pego a posição 0, pois só teremos um usuário com o login inserido
            //vocês deverão garantir que não teremos login´s iguais no banco de dados
            if (listaDePessoas.get(0).getSenha().equals(this.usuario.getSenha())) {
                //como o login e senha estão corretos posso então salvar o usuário como logado com sucesso
                //devem avisar que foi logado com sucesso !!
                usuario.setNome(listaDePessoas.get(0).getNome());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser", this.usuario);//usuario.getLogin());
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            }else{
                //Avisem que apesar do login estar correto a senha não está.
                System.out.println(" login esta correto , mas senha está errada");
                
            }
        }
    }
    
    public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("currentUser");
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

}
