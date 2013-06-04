package dom.autos;

import java.util.List;

import org.apache.isis.applib.AbstractContainedObject;
//import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;

import com.google.common.base.Objects;


import dom.autos.Autos;
import dom.autos.Autos.Marca;
 

@Named("Autos")
public class AutosServicio extends AbstractContainedObject{

 
	 public List<Autos> ListarAutos() {

		 final List<Autos> autos= allInstances(Autos.class);
 
		 return autos;
		 }
	
	 
	@MemberOrder(sequence = "2")
	public Autos CargarAuto(
	       @Named("Patente") String patente,
	       @Named("Marca") Marca marca, 
	       @Named("Modelo") String modelo) {
	     final String ownedBy = currentUserName();
	     return elAuto(patente,marca,modelo,ownedBy);
	   }
		 
	@Hidden // for use by fixtures
	public Autos elAuto(
		   String patente,
		   Marca marca, 
		   String modelo,
		   String userName) {
		 final Autos auto = newTransientInstance(Autos.class);
		   auto.setPatente(patente);
		   auto.setMarca(marca);
		   auto.setModelo(modelo);
		   auto.setOwnedBy(userName);
 
        // 
        // GMAP3: uncomment to use https://github.com/danhaywood/isis-wicket-gmap3        
        // toDoItem.setLocation(
        //    New Location(51.5172+random(-0.05, +0.05), 0.1182 + random(-0.05, +0.05)));
		//
		        
       persist(auto);
       return auto;
    }
	


	// {{ helpers
	protected boolean ownedByCurrentUser(final Autos t) {
	    return Objects.equal(t.getOwnedBy(), currentUserName());
	}
	protected String currentUserName() {
	    return getContainer().getUser().getName();
	}
	// }}
	



}