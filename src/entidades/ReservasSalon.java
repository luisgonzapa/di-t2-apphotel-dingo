/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rafae
 */
@Entity
@Table(name = "RESERVAS_SALON")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservasSalon.findAll", query = "SELECT r FROM ReservasSalon r")
    , @NamedQuery(name = "ReservasSalon.findByCod", query = "SELECT r FROM ReservasSalon r WHERE r.cod = :cod")
    , @NamedQuery(name = "ReservasSalon.findByTipo", query = "SELECT r FROM ReservasSalon r WHERE r.tipo = :tipo")
    , @NamedQuery(name = "ReservasSalon.findByNumPersonas", query = "SELECT r FROM ReservasSalon r WHERE r.numPersonas = :numPersonas")
    , @NamedQuery(name = "ReservasSalon.findByTipoCocina", query = "SELECT r FROM ReservasSalon r WHERE r.tipoCocina = :tipoCocina")
    , @NamedQuery(name = "ReservasSalon.findByFecha", query = "SELECT r FROM ReservasSalon r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "ReservasSalon.findByNumHabitaciones", query = "SELECT r FROM ReservasSalon r WHERE r.numHabitaciones = :numHabitaciones")
    , @NamedQuery(name = "ReservasSalon.findByNumDias", query = "SELECT r FROM ReservasSalon r WHERE r.numDias = :numDias")
    , @NamedQuery(name = "ReservasSalon.findByCliente", query = "SELECT r FROM ReservasSalon r WHERE r.dniCliente = :dniCliente")
})
public class ReservasSalon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COD")
    private Integer cod;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "NUM_PERSONAS")
    private int numPersonas;
    @Column(name = "TIPO_COCINA")
    private String tipoCocina;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "NUM_HABITACIONES")
    private Integer numHabitaciones;
    @Column(name = "NUM_DIAS")
    private Integer numDias;
    @JoinColumn(name = "DNI_CLIENTE", referencedColumnName = "DNI")
    @ManyToOne(optional = false)
    private Cliente dniCliente;

    public ReservasSalon() {
    }

    public ReservasSalon(Integer cod) {
        this.cod = cod;
    }

    public ReservasSalon(Integer cod, String tipo, int numPersonas, Date fecha) {
        this.cod = cod;
        this.tipo = tipo;
        this.numPersonas = numPersonas;
        this.fecha = fecha;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public String getTipoCocina() {
        return tipoCocina;
    }

    public void setTipoCocina(String tipoCocina) {
        this.tipoCocina = tipoCocina;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(Integer numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public Integer getNumDias() {
        return numDias;
    }

    public void setNumDias(Integer numDias) {
        this.numDias = numDias;
    }

    public Cliente getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(Cliente dniCliente) {
        this.dniCliente = dniCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cod != null ? cod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservasSalon)) {
            return false;
        }
        ReservasSalon other = (ReservasSalon) object;
        if ((this.cod == null && other.cod != null) || (this.cod != null && !this.cod.equals(other.cod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ReservasSalon[ cod=" + cod + " ]";
    }
    
}
