/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lmc_builder.phoniex.Xliff;

import org.jdom.Attribute;

/**
 *
 * @author Sean Mooney
 */
public class AltTrans extends XliffElement {

    private AltTrans() {
        super("alt-trans");
        Attribute altTransType = new Attribute("alttranstype", "proposal");
        setAttribute(altTransType);
        Attribute origin = new Attribute("origin", "lmc");
        setAttribute(origin);
    }

    public AltTrans(String altSource, String altSourceLang) {
        this();
        setSource(altSource, altSourceLang);
    }

    public AltTrans(String altSource, String altSourceLang, double quality) {
        this();
        setSource(altSource, altSourceLang);
        Attribute matchQuality = new Attribute("match-quality", "" + quality);
        setAttribute(matchQuality);
    }

    public AltTrans(String alt, String lang, String altSource, String altSourceLang) {
        this(altSource, altSourceLang);
        setTarget(alt, lang);

    }

    public AltTrans(String alt, String lang, String altSource, String altSourceLang, double quality) {
        this(alt, lang, altSource, altSourceLang);
        Attribute matchQuality = new Attribute("match-quality", "" + quality);
        setAttribute(matchQuality);
    }

    public AltTrans(Target t) {
        this();
        this.addContent(t);
    }

    /**
     * Get the value of target
     *
     * @return the value of target
     */
    public Target getTarget() {
        return (Target) getChild("target", xliff);
    }

    /**
     * Set the value of target
     *
     * @param target new value of target
     */
    public final void setTarget(String target, String lang) {

        replace("target", new Target(target, lang));
    }
    /**
     * returns the text of the target element
     *
     * @see Source
     * @return the text componant of the source element
     */
    public String getTargetText() {
        return getTarget().getText();
    }

    /**
     * Get the value of note
     *
     * @return the value of note
     * @see Note
     */
    public Note getNote() {
        return (Note) getChild("note", xliff);
    }

    /**
     * set the note element to the values supplied
     *
     * @param the text component of the note element
     * @see Note
     */
    public void setNote(String note) {

        replace("note", new Note(note));
    }

    /**
     * Get the value of source
     * @see Source
     * @return the value of source
     */
    public Source getSource() {
        return (Source) getChild("source", xliff);
    }

    /**
     * set the source element to the values supplied
     *
     * @param source the text component of the source element
     * @param lang the language of the source element
     * @see Source
     */
    public final void setSource(String source, String lang) {
        replace("source", new Source(source, lang));
    }
    /**
     * returns the text of the source element
     *
     * @see Source
     * @return the text componant of the source element
     */
    public String getSourceText() {
        return getSource().getText();
    }
}

   
