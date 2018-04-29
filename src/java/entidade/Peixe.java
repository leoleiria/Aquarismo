
package entidade;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author leole
 */
@Entity
public class Peixe implements Serializable{
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String especie;
    private int estoque;
    @ManyToOne
    private Aquario aqua;
    
    public Peixe(){
    }
    
    public Peixe(long id, String especie, int estoque, Aquario aqua){
        this.id = id;
        this.especie =especie;
        this.estoque = estoque;
        this.aqua = aqua;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public Aquario getAqua() {
        return aqua;
    }

    public void setAqua(Aquario aqua) {
        this.aqua = aqua;
    }
    
}
