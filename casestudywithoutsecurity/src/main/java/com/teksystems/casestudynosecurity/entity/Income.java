package com.teksystems.casestudynosecurity.entity;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity 
public class Income {
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Id
	    private int id;

	    @Column
	    private String name;
	    
	    @Column
	    private String description;
	    
	    @Column
	    private BigDecimal amount;
	    
	    @ManyToOne
	    @JoinColumn(name= "user", nullable=false)
	    private User user;

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public BigDecimal getAmount() {
	        return amount;
	    }

	    public void setAmount(BigDecimal amount) {
	        this.amount = amount;
	    }

	    public User getUser() {
	        return user;
	    }

	    public void setUser(User user) {
	        this.user = user;
	    }

	    @Override
	    public int hashCode() {
	        int hash = 7;
	        hash = 59 * hash + this.id;
	        hash = 59 * hash + Objects.hashCode(this.name);
	        hash = 59 * hash + Objects.hashCode(this.description);
	        hash = 59 * hash + Objects.hashCode(this.amount);
	        hash = 59 * hash + Objects.hashCode(this.user);
	        return hash;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }
	        if (obj == null) {
	            return false;
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        final Income other = (Income) obj;
	        if (this.id != other.id) {
	            return false;
	        }
	        if (!Objects.equals(this.name, other.name)) {
	            return false;
	        }
	        if (!Objects.equals(this.description, other.description)) {
	            return false;
	        }
	        if (!Objects.equals(this.amount, other.amount)) {
	            return false;
	        }
	        if (!Objects.equals(this.user, other.user)) {
	            return false;
	        }
	        return true;
	    }
    
}
