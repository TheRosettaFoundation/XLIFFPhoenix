/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex;

import java.util.List;
import org.jdom.Document;
import org.jdom.Element;



/**
 *
 * @author Admin
 */
public class StatBuilder {
    Document doc = new Document();

    public  StatBuilder(Document doc){
        this.doc = doc;
    }
    public Document build(Document doc){
         this.doc = doc;
         return build();
    }
     public Document build(){
         Element root = new Element("match-statistics");
         Document result= new Document(root);
         List<Element> matches = (List<Element>)Core.filter("//xliff:alt-trans", doc);
         if(matches.isEmpty()) matches=(List<Element>)Core.filter("//alt-trans", doc);
         int matchCount = matches.size();
         int mQ99,mQ95,mQ85,mQ75,mQ50,mQother;
         mQ99=mQ95=mQ85=mQ75=mQ50=mQother=0;
         for(Element e:matches){
             if(Double.parseDouble(e.getAttributeValue("match-quality"))>99)mQ99++;
             else if(Double.parseDouble(e.getAttributeValue("match-quality"))>95)mQ95++;
             else if(Double.parseDouble(e.getAttributeValue("match-quality"))>85)mQ85++;
             else if(Double.parseDouble(e.getAttributeValue("match-quality"))>75)mQ75++;
             else if(Double.parseDouble(e.getAttributeValue("match-quality"))>50)mQ50++;
             else mQother++;
         }
         if(matchCount!=0){
         Element match99= new Element("match-count");
         match99.setAttribute("range", "100%");
         match99.setAttribute("doc-percent", ""+(100*mQ99/matchCount));
         match99.addContent(""+mQ99);
         root.addContent(match99);
         Element match95= new Element("match-count");
         match95.setAttribute("range", "95%-99%");
         match95.setAttribute("doc-percent", ""+(100*mQ95/matchCount));
         match95.addContent(""+mQ95);
         root.addContent(match95);
         Element match85= new Element("match-count");
         match85.setAttribute("range", "85%-94%");
         match85.setAttribute("doc-percent", ""+(100*mQ85/matchCount));
         match85.addContent(""+mQ85);
         root.addContent(match85);
         Element match75= new Element("match-count");
         match75.setAttribute("range", "75%-84%");
         match75.setAttribute("doc-percent", ""+(100*mQ75/matchCount));
         match75.addContent(""+mQ75);
         root.addContent(match75);
         Element match50= new Element("match-count");
         match50.setAttribute("range", "50%-74%");
         match50.setAttribute("doc-percent", ""+(100*mQ50/matchCount));
         match50.addContent(""+mQ50);
         root.addContent(match50);
         Element matchOther= new Element("match-count");
         matchOther.setAttribute("range", "<50%");
         matchOther.setAttribute("doc-percent", ""+(100*mQother/matchCount));
         matchOther.addContent(""+mQother);
         root.addContent(matchOther);
         Element total= new Element("match-count");
         total.setAttribute("range", "Total");
         total.setAttribute("doc-percent", ""+(100*(mQother+mQ50+mQ75+mQ85+mQ95+mQ99)/matchCount));
         total.addContent(""+(mQother+mQ50+mQ75+mQ85+mQ95+mQ99));
         root.addContent(total);
         }
         else{
             Element total= new Element("match-count");
             total.setAttribute("range", "Total");
             total.setAttribute("doc-percent", "100");
             total.addContent("0");
             root.addContent(total);
         }
         return result;
    }
}
