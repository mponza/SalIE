

/* First created by JCasGen Thu Aug 09 14:11:06 CEST 2018 */
package de.mpg.mpi.uima.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Aug 09 16:07:33 CEST 2018
 * XML source: /Users/marcoponza/eclipse-workspace/UIMA/src/salieTypeSystemDescriptor.xml
 * @generated */
public class MinIEAttribution extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(MinIEAttribution.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected MinIEAttribution() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public MinIEAttribution(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public MinIEAttribution(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public MinIEAttribution(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: attributionConstituent

  /** getter for attributionConstituent - gets MinIE's attributionPhrase
   * @generated
   * @return value of the feature 
   */
  public Constituent getAttributionConstituent() {
    if (MinIEAttribution_Type.featOkTst && ((MinIEAttribution_Type)jcasType).casFeat_attributionConstituent == null)
      jcasType.jcas.throwFeatMissing("attributionConstituent", "de.mpg.mpi.uima.type.MinIEAttribution");
    return (Constituent)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEAttribution_Type)jcasType).casFeatCode_attributionConstituent)));}
    
  /** setter for attributionConstituent - sets MinIE's attributionPhrase 
   * @generated
   * @param v value to set into the feature 
   */
  public void setAttributionConstituent(Constituent v) {
    if (MinIEAttribution_Type.featOkTst && ((MinIEAttribution_Type)jcasType).casFeat_attributionConstituent == null)
      jcasType.jcas.throwFeatMissing("attributionConstituent", "de.mpg.mpi.uima.type.MinIEAttribution");
    jcasType.ll_cas.ll_setRefValue(addr, ((MinIEAttribution_Type)jcasType).casFeatCode_attributionConstituent, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: modalityType

  /** getter for modalityType - gets 
   * @generated
   * @return value of the feature 
   */
  public String getModalityType() {
    if (MinIEAttribution_Type.featOkTst && ((MinIEAttribution_Type)jcasType).casFeat_modalityType == null)
      jcasType.jcas.throwFeatMissing("modalityType", "de.mpg.mpi.uima.type.MinIEAttribution");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MinIEAttribution_Type)jcasType).casFeatCode_modalityType);}
    
  /** setter for modalityType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setModalityType(String v) {
    if (MinIEAttribution_Type.featOkTst && ((MinIEAttribution_Type)jcasType).casFeat_modalityType == null)
      jcasType.jcas.throwFeatMissing("modalityType", "de.mpg.mpi.uima.type.MinIEAttribution");
    jcasType.ll_cas.ll_setStringValue(addr, ((MinIEAttribution_Type)jcasType).casFeatCode_modalityType, v);}    
   
    
  //*--------------*
  //* Feature: polarityType

  /** getter for polarityType - gets 
   * @generated
   * @return value of the feature 
   */
  public String getPolarityType() {
    if (MinIEAttribution_Type.featOkTst && ((MinIEAttribution_Type)jcasType).casFeat_polarityType == null)
      jcasType.jcas.throwFeatMissing("polarityType", "de.mpg.mpi.uima.type.MinIEAttribution");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MinIEAttribution_Type)jcasType).casFeatCode_polarityType);}
    
  /** setter for polarityType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPolarityType(String v) {
    if (MinIEAttribution_Type.featOkTst && ((MinIEAttribution_Type)jcasType).casFeat_polarityType == null)
      jcasType.jcas.throwFeatMissing("polarityType", "de.mpg.mpi.uima.type.MinIEAttribution");
    jcasType.ll_cas.ll_setStringValue(addr, ((MinIEAttribution_Type)jcasType).casFeatCode_polarityType, v);}    
  }

    