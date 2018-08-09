

/* First created by JCasGen Tue Aug 07 17:11:25 CEST 2018 */
package de.mpg.mpi.uima.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Aug 09 16:07:33 CEST 2018
 * XML source: /Users/marcoponza/eclipse-workspace/UIMA/src/salieTypeSystemDescriptor.xml
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
  public MinIEAttribution getAttribution() {
    if (MinIEOpenFact_Type.featOkTst && ((MinIEOpenFact_Type)jcasType).casFeat_attribution == null)
      jcasType.jcas.throwFeatMissing("attribution", "de.mpg.mpi.uima.type.MinIEOpenFact");
    return (MinIEAttribution)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEOpenFact_Type)jcasType).casFeatCode_attribution)));}
    
  /** setter for attribution - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAttribution(MinIEAttribution v) {
    if (MinIEOpenFact_Type.featOkTst && ((MinIEOpenFact_Type)jcasType).casFeat_attribution == null)
      jcasType.jcas.throwFeatMissing("attribution", "de.mpg.mpi.uima.type.MinIEOpenFact");
    jcasType.ll_cas.ll_setRefValue(addr, ((MinIEOpenFact_Type)jcasType).casFeatCode_attribution, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: polarity

  /** getter for polarity - gets 
   * @generated
   * @return value of the feature 
   */
  public MinIEPolarity getPolarity() {
    if (MinIEOpenFact_Type.featOkTst && ((MinIEOpenFact_Type)jcasType).casFeat_polarity == null)
      jcasType.jcas.throwFeatMissing("polarity", "de.mpg.mpi.uima.type.MinIEOpenFact");
    return (MinIEPolarity)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEOpenFact_Type)jcasType).casFeatCode_polarity)));}
    
  /** setter for polarity - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setPolarity(MinIEPolarity v) {
    if (MinIEOpenFact_Type.featOkTst && ((MinIEOpenFact_Type)jcasType).casFeat_polarity == null)
      jcasType.jcas.throwFeatMissing("polarity", "de.mpg.mpi.uima.type.MinIEOpenFact");
    jcasType.ll_cas.ll_setRefValue(addr, ((MinIEOpenFact_Type)jcasType).casFeatCode_polarity, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: modality

  /** getter for modality - gets 
   * @generated
   * @return value of the feature 
   */
  public MinIEModality getModality() {
    if (MinIEOpenFact_Type.featOkTst && ((MinIEOpenFact_Type)jcasType).casFeat_modality == null)
      jcasType.jcas.throwFeatMissing("modality", "de.mpg.mpi.uima.type.MinIEOpenFact");
    return (MinIEModality)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEOpenFact_Type)jcasType).casFeatCode_modality)));}
    
  /** setter for modality - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setModality(MinIEModality v) {
    if (MinIEOpenFact_Type.featOkTst && ((MinIEOpenFact_Type)jcasType).casFeat_modality == null)
      jcasType.jcas.throwFeatMissing("modality", "de.mpg.mpi.uima.type.MinIEOpenFact");
    jcasType.ll_cas.ll_setRefValue(addr, ((MinIEOpenFact_Type)jcasType).casFeatCode_modality, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    