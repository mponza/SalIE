

/* First created by JCasGen Tue Aug 07 17:11:25 CEST 2018 */
package de.mpg.mpi.uima.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Tue Aug 07 17:14:20 CEST 2018
 * XML source: /Users/marcoponza/eclipse-workspace/UIMA/openFactTypeSystemDescriptor.xml
 * @generated */
public class MinIEOpenFact extends OpenFact {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(MinIEOpenFact.class);
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
  protected MinIEOpenFact() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public MinIEOpenFact(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public MinIEOpenFact(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public MinIEOpenFact(JCas jcas, int begin, int end) {
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
  //* Feature: attribution

  /** getter for attribution - gets 
   * @generated
   * @return value of the feature 
   */
  public String getAttribution() {
    if (MinIEOpenFact_Type.featOkTst && ((MinIEOpenFact_Type)jcasType).casFeat_attribution == null)
      jcasType.jcas.throwFeatMissing("attribution", "de.mpg.mpi.uima.type.MinIEOpenFact");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MinIEOpenFact_Type)jcasType).casFeatCode_attribution);}
    
  /** setter for attribution - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAttribution(String v) {
    if (MinIEOpenFact_Type.featOkTst && ((MinIEOpenFact_Type)jcasType).casFeat_attribution == null)
      jcasType.jcas.throwFeatMissing("attribution", "de.mpg.mpi.uima.type.MinIEOpenFact");
    jcasType.ll_cas.ll_setStringValue(addr, ((MinIEOpenFact_Type)jcasType).casFeatCode_attribution, v);}    
   
    
  //*--------------*
  //* Feature: polarity

  /** getter for polarity - gets 
   * @generated
   * @return value of the feature 
   */
  public String getPolarity() {
    if (MinIEOpenFact_Type.featOkTst && ((MinIEOpenFact_Type)jcasType).casFeat_polarity == null)
      jcasType.jcas.throwFeatMissing("polarity", "de.mpg.mpi.uima.type.MinIEOpenFact");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MinIEOpenFact_Type)jcasType).casFeatCode_polarity);}
    
  /** setter for polarity - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPolarity(String v) {
    if (MinIEOpenFact_Type.featOkTst && ((MinIEOpenFact_Type)jcasType).casFeat_polarity == null)
      jcasType.jcas.throwFeatMissing("polarity", "de.mpg.mpi.uima.type.MinIEOpenFact");
    jcasType.ll_cas.ll_setStringValue(addr, ((MinIEOpenFact_Type)jcasType).casFeatCode_polarity, v);}    
   
    
  //*--------------*
  //* Feature: modality

  /** getter for modality - gets 
   * @generated
   * @return value of the feature 
   */
  public String getModality() {
    if (MinIEOpenFact_Type.featOkTst && ((MinIEOpenFact_Type)jcasType).casFeat_modality == null)
      jcasType.jcas.throwFeatMissing("modality", "de.mpg.mpi.uima.type.MinIEOpenFact");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MinIEOpenFact_Type)jcasType).casFeatCode_modality);}
    
  /** setter for modality - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setModality(String v) {
    if (MinIEOpenFact_Type.featOkTst && ((MinIEOpenFact_Type)jcasType).casFeat_modality == null)
      jcasType.jcas.throwFeatMissing("modality", "de.mpg.mpi.uima.type.MinIEOpenFact");
    jcasType.ll_cas.ll_setStringValue(addr, ((MinIEOpenFact_Type)jcasType).casFeatCode_modality, v);}    
   
    
  //*--------------*
  //* Feature: quantity

  /** getter for quantity - gets 
   * @generated
   * @return value of the feature 
   */
  public String getQuantity() {
    if (MinIEOpenFact_Type.featOkTst && ((MinIEOpenFact_Type)jcasType).casFeat_quantity == null)
      jcasType.jcas.throwFeatMissing("quantity", "de.mpg.mpi.uima.type.MinIEOpenFact");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MinIEOpenFact_Type)jcasType).casFeatCode_quantity);}
    
  /** setter for quantity - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuantity(String v) {
    if (MinIEOpenFact_Type.featOkTst && ((MinIEOpenFact_Type)jcasType).casFeat_quantity == null)
      jcasType.jcas.throwFeatMissing("quantity", "de.mpg.mpi.uima.type.MinIEOpenFact");
    jcasType.ll_cas.ll_setStringValue(addr, ((MinIEOpenFact_Type)jcasType).casFeatCode_quantity, v);}    
   
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets 
   * @generated
   * @return value of the feature 
   */
  public String getText() {
    if (MinIEOpenFact_Type.featOkTst && ((MinIEOpenFact_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "de.mpg.mpi.uima.type.MinIEOpenFact");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MinIEOpenFact_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (MinIEOpenFact_Type.featOkTst && ((MinIEOpenFact_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "de.mpg.mpi.uima.type.MinIEOpenFact");
    jcasType.ll_cas.ll_setStringValue(addr, ((MinIEOpenFact_Type)jcasType).casFeatCode_text, v);}    
  }

    