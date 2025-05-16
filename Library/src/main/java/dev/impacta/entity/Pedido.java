package dev.impacta.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmprestimo;
	private String dt_retirada;
    private String dt_devolucao;
    private String status;
    
    @ManyToOne
	@JoinColumn(name = "id_livro")
    private Livro livro;


    public Pedido() {

    }


    public Livro getLivro() {
        return livro;
    }

    public void setIdLivro(Livro livro) {
        this.livro = livro;
    }

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }
    
    public String getDt_retirada() {
		return dt_retirada;
	}

	public void setDt_retirada(String dt_retirada) {
		this.dt_retirada = dt_retirada;
	}

	public String getDt_devolucao() {
		return dt_devolucao;
	}

	public void setDt_devolucao(String dt_devolucao) {
		this.dt_devolucao = dt_devolucao;
	}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}