/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package phoniex.Xliff;

import lmc_builder.phoniex.Xliff.XliffElement;
import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.ProcessingInstruction;

/**
 *
 * @author Admin
 */
public class MetaData extends XliffElement {

    public MetaData() {
        super("metadata");
        setNamespace(Namespace.NO_NAMESPACE);
    }

    public void setOrigin(String origin) {
        setAttribute("original", origin);
    }
//     public void setDatatype(String datatype){
//         setAttribute("datatype", datatype);
//     }

    public void setVersion(String version) {
        setAttribute("version", version);
    }

    public void setDate(String date) {
        setAttribute("date", date);
    }

    public void setSourceLanguage(String sourceLang) {
        setAttribute("source-language", sourceLang);
    }

    public void setTargetLanguage(String targetLang) {
        setAttribute("target-language", targetLang);
    }

    public void setToolId(String ToolId) {
        setAttribute("tool-id", ToolId);
    }

    public void setToolCompany(String ToolCompany) {
        setAttribute("tool-company", ToolCompany);
    }

    public void setToolName(String ToolName) {
        setAttribute("tool-name", ToolName);
    }

    public void setToolVersion(String ToolVersion) {
        setAttribute("tool-version", ToolVersion);
    }

    public void setState(String State) {
        setAttribute("state", State);
    }

    public void setStateQualifier(String StateQualifier) {
        setAttribute("state-qualifier", StateQualifier);
    }

    public void setjobId(String jobId) {
        setAttribute("job-id", jobId);
    }

    public void setCompanyName(String CompanyName) {
        setAttribute("company-name", CompanyName);
    }

    public void setContactName(String ContactName) {
        setAttribute("contact-name", ContactName);
    }

    public void setContactEmail(String ContactEmail) {
        setAttribute("contact-email", ContactEmail);
    }

    public void setCategory(String category) {
        setAttribute("category", category);
    }

    public Attribute getOrigin() {
        return getAttribute("original");
    }

    public Attribute getDatatype() {
        return getAttribute("datatype");
    }

    public Attribute getVersion() {
        return getAttribute("version");
    }

    public Attribute getDate() {
        return getAttribute("date");
    }

    public Attribute getSourceLanguage() {
        return getAttribute("source-language");
    }

    public Attribute getTargetLanguage() {
        return getAttribute("target-language");
    }

    public Attribute getToolId() {
        return getAttribute("tool-id");
    }

    public Attribute getToolCompany() {
        return getAttribute("tool-company");
    }

    public Attribute getToolName() {
        return getAttribute("tool-name");
    }

    public Attribute getToolVersion() {
        return getAttribute("tool-version");
    }

    public Attribute getState() {
        return getAttribute("state");
    }

    public Attribute getStateQualifier() {
        return getAttribute("state-qualifier");
    }

    public Attribute getjobId() {
        return getAttribute("job-id");
    }

    public Attribute getCompanyName() {
        return getAttribute("company-name");
    }

    public Attribute getContactName() {
        return getAttribute("contact-name");
    }

    public Attribute getContactEmail() {
        return getAttribute("contact-email");
    }

    public Attribute getCategory() {
        return getAttribute("category");
    }

    public void extract(Element trans) {
        Element file = trans.getParentElement().getParentElement();
        Element header = file.getChild("header", XliffElement.xliff);
        Element phaseGroup =header.getChild("phase-group", XliffElement.xliff);
        Element phase = null;
        if(phaseGroup!=null)phase=phaseGroup.getChild("phase", xliff);
        setCategory(attCheck("category", file));
//        if(phase!=null){
            setCompanyName(attCheck("company-name", phase));
            setContactEmail(attCheck("contact-email", phase));
            setContactName(attCheck("contact-name", phase));
//        }
        setDate(attCheck("date", file));
//        if(phase!=null)
            setjobId(attCheck("job-id", phase));
        setOrigin(attCheck("orignal", file));
        if (getOrigin().getValue() == "") {
            setOrigin(attCheck("orignal", xliff, file));
        }
        setSourceLanguage(attCheck("source-language", file));
        setState(attCheck("state", trans.getChild("target", XliffElement.xliff)));
        setStateQualifier(attCheck("state-qualifier", trans.getChild("target", XliffElement.xliff)));
        setTargetLanguage(attCheck("target-language", file));
        setToolCompany(attCheck("tool-company", header.getChild("tool", XliffElement.xliff)));
        setToolId(attCheck("tool-id", file));
        if(getToolId().getValue()=="") setToolId(attCheck("tool-id", header.getChild("tool",XliffElement.xliff)));
        setToolName(attCheck("tool-name", header.getChild("tool", XliffElement.xliff)));
        setToolVersion(attCheck("tool-version", header.getChild("tool", XliffElement.xliff)));
        
    }

    public static void extractPI(Element trans, Element alt) {
        Element file = trans.getParentElement().getParentElement();
        Element header = file.getChild("header", XliffElement.xliff);
        Element phaseGroup =header.getChild("phase-group", XliffElement.xliff);
        Element phase = null;
        if(phaseGroup!=null)phase=phaseGroup.getChild("phase", xliff);

        alt.addContent(new ProcessingInstruction("category", attCheck("category", file)));
//        if(phase!=null){
            alt.addContent(new ProcessingInstruction("company-name", attCheck("company-name", phase)));
            alt.addContent(new ProcessingInstruction("contact-email", attCheck("contact-email", phase)));
            alt.addContent(new ProcessingInstruction("contact-name", attCheck("contact-name", phase)));
//        }
        alt.addContent(new ProcessingInstruction("date", attCheck("date", file)));
//        if(phase!=null)
            alt.addContent(new ProcessingInstruction("job-id", attCheck("job-id", phase)));
        alt.addContent(new ProcessingInstruction("original", attCheck("original", file)));
        alt.addContent(new ProcessingInstruction("source-language", attCheck("source-language", file)));
        alt.addContent(new ProcessingInstruction("state", attCheck("state", trans.getChild("target", XliffElement.xliff))));
        alt.addContent(new ProcessingInstruction("state-qualifier", attCheck("state-qualifier", trans.getChild("target", XliffElement.xliff))));
        alt.addContent(new ProcessingInstruction("target-language", attCheck("target-language", file)));
        alt.addContent(new ProcessingInstruction("tool-company", attCheck("tool-company", header.getChild("tool", XliffElement.xliff))));
        alt.addContent(new ProcessingInstruction("tool-id", attCheck("tool-id", header.getChild("tool", XliffElement.xliff))));
        alt.addContent(new ProcessingInstruction("tool-name", attCheck("tool-name", header.getChild("tool", XliffElement.xliff))));
        alt.addContent(new ProcessingInstruction("tool-version", attCheck("tool-version", header.getChild("tool", XliffElement.xliff))));


    }

    public void addData(ProcessingInstruction pi){
        if(pi.getTarget().equalsIgnoreCase("category")) setCategory(pi.getValue());
        else if(pi.getTarget().equalsIgnoreCase("company-name")) setCompanyName(pi.getValue());
        else if(pi.getTarget().equalsIgnoreCase("contact-email")) setContactEmail(pi.getValue());
        else if(pi.getTarget().equalsIgnoreCase("contact-name")) setContactName(pi.getValue());
        else if(pi.getTarget().equalsIgnoreCase("date")) setDate(pi.getValue());
        else if(pi.getTarget().equalsIgnoreCase("job-id")) setjobId(pi.getValue());
        else if(pi.getTarget().equalsIgnoreCase("original")) setOrigin(pi.getValue());
        else if(pi.getTarget().equalsIgnoreCase("source-language")) setSourceLanguage(pi.getValue());
        else if(pi.getTarget().equalsIgnoreCase("state")) setState(pi.getValue());
        else if(pi.getTarget().equalsIgnoreCase("state-qualifier")) setStateQualifier(pi.getValue());
        else if(pi.getTarget().equalsIgnoreCase("target-language")) setTargetLanguage(pi.getValue());
        else if(pi.getTarget().equalsIgnoreCase("tool-company")) setToolCompany(pi.getValue());
        else if(pi.getTarget().equalsIgnoreCase("tool-id")) setToolCompany(pi.getValue());
        else if(pi.getTarget().equalsIgnoreCase("tool-name")) setToolName(pi.getValue());
        else if(pi.getTarget().equalsIgnoreCase("tool-version")) setToolName(pi.getValue());
        else setAttribute(pi.getTarget(), pi.getValue());

    }
}
