package org.alb.sse.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author alorie
 *
 */
@Named
@RequestScoped
public class StockTickerBean {

    String symbols;

    public String stockInfo(){
	return "";
    }
    
    public String reset(){
	return "";
    }
    
    public String getSymbols() {
        return symbols;
    }
    public void setSymbols(String symbols) {
        this.symbols = symbols;
    }
}
