package est.dsic.jee.controllers;


import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;


import jakarta.enterprise.context.SessionScoped;

import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;

@Named
@SessionScoped
public class Internationalisation  implements Serializable{
  
   private String locale;

   private static Map<String,Object> countries;
      static{
      countries = new LinkedHashMap<String,Object>();
      countries.put("English", Locale.ENGLISH);
      countries.put("French", Locale.FRENCH);
   }

   public Map<String, Object> getCountries() {
      return countries;
   }

   public String getLocale() {
      return locale;
   }

   public void setLocale(String locale) {
      this.locale = locale;
   }

   //value change event listener
   public void localeChanged(ValueChangeEvent e) {
      String newLocaleValue = e.getNewValue().toString();
      System.out.println(e.getNewValue().toString());
      for (Map.Entry<String, Object> entry : countries.entrySet()) { 
        if(entry.getValue().toString().equals(newLocaleValue)) {
            FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale)entry.getValue());         
        }
      }
   }
}
