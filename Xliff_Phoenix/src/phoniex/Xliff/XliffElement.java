/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex.Xliff;


import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.Namespace;


/**
 * This is an extention of the Element class to include
 * the xliff namespace
 * @author Sean Mooney
 * @see         Element
 */

public class XliffElement extends Element {
 /**
 * this is the Xliff 1.2 namespace
 * @see         Namespace
 */
public final static Namespace xliff = Namespace.getNamespace("xliff", "urn:oasis:names:tc:xliff:document:1.2");
public final static Namespace xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");

 /**
 * Constructs a standard Xliff Element.
 * <P>
 * The name argument must specify an Element name as a {@link String}.<br/>
 * This method should be invoked by any subclass during constrution of an Xlif Element
 * </P>
 * @param  name the name of an Xliff Element (xliff,file,trans-unit etc..)
 * @see         Element
 */

    public XliffElement(String name){
        super(name);
    }
  
/**
 * adds text to this Element
 *
 * @param  text the text component of this element
 *
 */
    
    public final void addText(String text){
        addContent(text);
    }
/**
 * set the xml:lang attribute of this element
 *
 * @param  lang the value of the xml:lang attribute
 *
 */
    public void setLanguage(String lang){
        setAttribute("lang", lang,Namespace.XML_NAMESPACE);
    }
/**
 * replaces the element specified with the element supplied
 *
 * @param  name the name of the element to be removed
 * @param  x    the XliffElement to add
 *
 */
    protected void replace(String name,XliffElement x){
        removeChild(name,xliff);
        addContent(x);
    }
/**
 * a helper funtion that checks the specified {@link Attribute} exits <P/>
 * If the attribute dose not exisit or the element is null the empty string is returned<br/>
 *
 * @param  name the name of the element to be removed
 * @param  e    the Element to add
 * @return String the value of the specified attribut of the supplyied element
 * @see  Attribute
 * @see  String
 *
 */
    public static String attCheck(String name, Element e){
        if(e==null)return "";
        Attribute at = e.getAttribute(name);
        if(at==null)return"";
        else return at.getValue().trim();
     }
/**
 *
 * a helper funtion that checks the specified {@link Attribute} exits <P/>
 * If the attribute dose not exisit or the element is null the empty string is returned<br/>
 *
 * @param  name the name of the element to be removed
 * @param  n    the nameSpace of the Attribute
 * @param  e    Element to add
 * @return String the value of the specified attribut of the supplyied element
 * @see  Attribute
 * @see  String
 *
 */
     public static String attCheck(String name,Namespace n, Element e){
        if(e==null)return "";
        Attribute at = e.getAttribute(name,n);
        if(at==null)return"";
        else return at.getValue().trim();
     }



}
