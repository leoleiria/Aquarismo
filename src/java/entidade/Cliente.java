
package entidade;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leole
 */
@Entity
@XmlRootElement
public class Cliente implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;

    public Cliente() {
    }

    public Cliente(long id, String nome) {
        this.id = id;
        this.nome = nome;
        
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }   

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
