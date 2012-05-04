/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex;

import org.jdom.Document;
import org.jdom.Element;

/**
 *
 * @author Admin
 */
public class Filter implements IFilter{

    private String fString = "/lmc:lmc/lmc:body/xliff:xliff";
    private String format = "";
    private String slang = "";
    private String tlang = "";
    private String tool = "";
    private String startDate = "";
    private String endDate = "";
    private String file = "";
    private String version = "";
    private String companyName = "";
    private String toolVersion="";
    private String email = "";
    private String contactName = "";
    private String jobId = "";
    private String feild = "";
    private String transUnitStatus = "";
    private String transUnitState = "";
    private String approved = "";
    private String translate = "";
    private String toolCompany = "";
    private String toolId = "";

    private Document doc=null;
    private static Filter filter = null;

    private Filter(Document lmc) {
        this.doc=lmc;
        Filter.filter=this;
    }


    public static Filter newFilter(Document lmc){
        if(filter==null){
            Filter.filter= new Filter(lmc);
        }
        else{
         filter.doc=lmc;
         filter.inisialise();
        }
        return filter;
    }

    public static Filter getFilter() {
        return filter;
    }


     private void inisialise(){
        fString = "/lmc:lmc/lmc:body/xliff:xliff";
        format = "";
        slang = "";
        tlang = "";
        tool = "";
        startDate = "";
        endDate = "";
        file = "";
        version = "";
        companyName = "";
        email = "";
        contactName = "";
        jobId = "";
        feild = "";
        transUnitStatus = "";
        transUnitState = "";
        approved = "";
        translate = "";
        toolCompany = "";
        toolId = "";
        toolVersion="";
     }


    @Override
    public Document refine(){
        Document result =null;
        fString = "/lmc:lmc/lmc:body/xliff:xliff";
    // the order of the addtions is important
        fString +=version;
        fString += file;
        fString += format;
        fString += slang;
        fString += tlang;
        fString += tool;
        fString += startDate + endDate;
        fString += transUnitState;
        fString +=feild;
        fString += transUnitStatus + toolId + toolCompany+toolVersion;
        fString += companyName + email + translate + contactName + jobId + approved ;
        
        Element filtered = Core.select(fString,doc);
        result =new Document(filtered);
        
        
        return result;
    }
    @Override
    public Document refine(Document lmc){
        this.doc=lmc;
        return refine();
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = (approved.trim().equalsIgnoreCase("") || approved.trim().equalsIgnoreCase("any")) ? "" : "[.//xliff:trans-unit[@approved=\"" + approved.trim() + "\"]]";
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = (companyName.trim().equalsIgnoreCase("") || companyName.trim().equalsIgnoreCase("any")) ? "" : "[.//xliff:header//xliff:phase[@company-name=\"" + companyName.trim() + "\"]]";
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = (contactName.trim().equalsIgnoreCase("") || contactName.trim().equalsIgnoreCase("any")) ? "" : "[.//xliff:header//xliff:phase[@contact-name=\"" + contactName.trim() + "\"]]";
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String date) {
        this.startDate = (date.trim().equalsIgnoreCase("") || date.trim().equalsIgnoreCase("YYYY-MM-DDThh:mm:ssZ")) ? "" : "[phoenix:dateAfter(@date,\"" + date.trim() + "\")]";
    }
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String date) {
        this.endDate = (date.trim().equalsIgnoreCase("") || date.trim().equalsIgnoreCase("YYYY-MM-DDThh:mm:ssZ")) ? "" : "[phoenix:dateBefore(@date,\"" + date.trim() + "\")]";
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = (email.trim().equalsIgnoreCase("") || email.trim().equalsIgnoreCase("any")) ? "" : "[.//xliff:header//xliff:phase[@contact-email=\"" + email.trim() + "\"]]";
    }

    public String getFeild() {
        return feild;
    }

    public void setFeild(String feild) {
        this.feild = (feild.trim().equalsIgnoreCase("") || feild.trim().equalsIgnoreCase("any")) ? "" : "[@category=\"" + feild.trim() + "\"]";
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = (file.trim().equalsIgnoreCase("") || file.trim().equalsIgnoreCase("any")) ? "/xliff:file" : "/xliff:file[@original=\"" + file.trim() + "\"]";
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = (format.trim().equalsIgnoreCase("") || format.trim().equalsIgnoreCase("any")) ? "" : "[@datatype=\"" + format.trim() + "\"]";
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = (jobId.trim().equalsIgnoreCase("") || jobId.trim().equalsIgnoreCase("any")) ? "" : "[.//xliff:header//xliff:phase[@job-id=\"" + jobId.trim() + "\"]]";
    }

    public String getToolVersion() {
        return toolVersion;
    }

    public void setToolVersion(String toolVersion) {
        this.toolVersion = (toolVersion.trim().equalsIgnoreCase("") || toolVersion.trim().equalsIgnoreCase("any")) ? "" : "[.//xliff:tool[@tool-version=\"" + toolVersion.trim() + "\"]]";
    }

    public String getSlang() {
        return slang;
    }

    public void setSlang(String slang) {
        this.slang = (slang.trim().equalsIgnoreCase("") || slang.trim().equalsIgnoreCase("any")) ? "" : "[@source-language=\"" + slang.trim() + "\"]";
    }

    public String getTlang() {
        return tlang;
    }

    public void setTlang(String tlang) {
        this.tlang = (tlang.trim().equalsIgnoreCase("") || tlang.trim().equalsIgnoreCase("any")) ? "" : "[@target-language=\"" + tlang.trim() + "\"]";
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = (tool.trim().equalsIgnoreCase("") || tool.trim().equalsIgnoreCase("any")) ? "" : "[.//xliff:header//xliff:tool[@tool-name =\"" + tool.trim() + "\"]]";
    }

    public String getToolCompany() {
        return toolCompany;
    }

    public void setToolCompany(String toolCompany) {
        this.toolCompany = (toolCompany.trim().equalsIgnoreCase("") || toolCompany.trim().equalsIgnoreCase("any")) ? "" : "[.//xliff:tool[@tool-company=\"" + toolCompany.trim() + "\"]]";
    }

    public String getToolId() {
        return toolId;
    }

    public void setToolId(String toolId) {
        this.toolId = (toolId.trim().equalsIgnoreCase("") || toolId.trim().equalsIgnoreCase("any")) ? "" : "[@tool-id=\"" + toolId.trim() + "\"]";
    }

    public String getTransUnitState() {
        return transUnitState;
    }

    public void setTransUnitState(String transUnitState) {
        this.transUnitState = (transUnitState.trim().equalsIgnoreCase("") || transUnitState.trim().equalsIgnoreCase("any")) ? "" : "[.//xliff:target[@state=\"" + transUnitState.trim() + "\"]]";
    }

    public String getTransUnitStatus() {
        return transUnitStatus;
    }

    public void setTransUnitStatus(String transUnitStatus) {
        this.transUnitStatus = (transUnitStatus.trim().equalsIgnoreCase("") || transUnitStatus.trim().equalsIgnoreCase("any")) ? "" : "[.//xliff:target[@state-qualifier\"" + transUnitStatus.trim() + "\"]]";
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = (translate.trim().equalsIgnoreCase("") || translate.trim().equalsIgnoreCase("any")) ? "" : "[.//xliff:trans-unit[@translate=\"" + translate.trim() + "\"]]";
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = (version.trim().equalsIgnoreCase("") || version.trim().equalsIgnoreCase("any")) ? "" : "[@version=\"" + version.trim() + "\"]";
    }










}
