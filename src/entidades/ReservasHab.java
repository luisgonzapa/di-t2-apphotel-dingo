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
@Table(name = "RESERVAS_HAB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservasHab.findAll", query = "SELECT r FROM ReservasHab r")
    , @NamedQuery(name = "ReservasHab.findByCod", query = "SELECT r FROM ReservasHab r WHERE r.cod = :cod")
    , @NamedQuery(name = "ReservasHab.findByFechaEntrada", query = "SELECT r FROM ReservasHab r WHERE r.fechaEntrada = :fechaEntrada")
    , @NamedQuery(name = "ReservasHab.findByFechaSalida", query = "SELECT r FROM ReservasHab r WHERE r.fechaSalida = :fechaSalida")
    , @NamedQuery(name = "ReservasHab.findByNumHabitaciones", query = "SELECT r FROM ReservasHab r WHERE r.numHabitaciones = :numHabitaciones")
    , @NamedQuery(name = "ReservasHab.findByTipoHabitacion", query = "SELECT r FROM ReservasHab r WHERE r.tipoHabitacion = :tipoHabitacion")
    , @NamedQuery(name = "ReservasHab.findByCliente", query = "SELECT r FROM ReservasHab r WHERE r.dniCliente = :dniCliente")
})
public class ReservasHab implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUM_HABITACIONES")
    private int numHabitaciones;
    @Column(name = "FUMADOR")
    private Boolean fumador;
    @Column(name = "REGIMEN")
    private String regimen;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COD")
    private Integer cod;
    @Column(name = "FECHA_ENTRADA")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrada;
    @Column(name = "FECHA_SALIDA")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @Column(name = "TIPO_HABITACION")
    private String tipoHabitacion;
    @JoinColumn(name = "DNI_CLIENTE", referencedColumnName = "DNI")
    @ManyToOne(optional = false)
    private Cliente dniCliente;

    public ReservasHab() {
    }

    public ReservasHab(Integer cod) {
        this.cod = cod;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Integer getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(Integer numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
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
        if (!(object instanceof ReservasHab)) {
            return false;
        }
        ReservasHab other = (ReservasHab) object;
        if ((this.cod == null && other.cod != null) || (this.cod != null && !this.cod.equals(other.cod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ReservasHab[ cod=" + cod + " ]";
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public Boolean getFumador() {
        return fumador;
    }

    public void setFumador(Boolean fumador) {
        this.fumador = fumador;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }
    
}
