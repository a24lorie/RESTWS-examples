package org.alb.sse.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author alorie
 *
 */
@Named
@RequestScoped
public class RssBean {

    private String symbols;
    
    
    public String stockNews(){
	return null;
    }


    public String getSymbols() {
        return symbols;
    }
    public void setSymbols(String symbols) {
        this.symbols = symbols;
    }
}
