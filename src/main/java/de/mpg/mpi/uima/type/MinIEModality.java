

/* First created by JCasGen Thu Aug 09 14:11:06 CEST 2018 */
package de.mpg.mpi.uima.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Thu Aug 09 16:07:33 CEST 2018
 * XML source: /Users/marcoponza/eclipse-workspace/UIMA/src/salieTypeSystemDescriptor.xml
 * @generated */
public class MinIEModality extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(MinIEModality.class);
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
  protected MinIEModality() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public MinIEModality(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public MinIEModality(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public MinIEModality(JCas jcas, int begin, int end) {
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
  //* Feature: possibilityTokens

  /** getter for possibilityTokens - gets MinIE’s possibilityWords
   * @generated
   * @return value of the feature 
   */
  public FSArray getPossibilityTokens() {
    if (MinIEModality_Type.featOkTst && ((MinIEModality_Type)jcasType).casFeat_possibilityTokens == null)
      jcasType.jcas.throwFeatMissing("possibilityTokens", "de.mpg.mpi.uima.type.MinIEModality");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEModality_Type)jcasType).casFeatCode_possibilityTokens)));}
    
  /** setter for possibilityTokens - sets MinIE’s possibilityWords 
   * @generated
   * @param v value to set into the feature 
   */
  public void setPossibilityTokens(FSArray v) {
    if (MinIEModality_Type.featOkTst && ((MinIEModality_Type)jcasType).casFeat_possibilityTokens == null)
      jcasType.jcas.throwFeatMissing("possibilityTokens", "de.mpg.mpi.uima.type.MinIEModality");
    jcasType.ll_cas.ll_setRefValue(addr, ((MinIEModality_Type)jcasType).casFeatCode_possibilityTokens, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for possibilityTokens - gets an indexed value - MinIE’s possibilityWords
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public TOP getPossibilityTokens(int i) {
    if (MinIEModality_Type.featOkTst && ((MinIEModality_Type)jcasType).casFeat_possibilityTokens == null)
      jcasType.jcas.throwFeatMissing("possibilityTokens", "de.mpg.mpi.uima.type.MinIEModality");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEModality_Type)jcasType).casFeatCode_possibilityTokens), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEModality_Type)jcasType).casFeatCode_possibilityTokens), i)));}

  /** indexed setter for possibilityTokens - sets an indexed value - MinIE’s possibilityWords
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setPossibilityTokens(int i, TOP v) { 
    if (MinIEModality_Type.featOkTst && ((MinIEModality_Type)jcasType).casFeat_possibilityTokens == null)
      jcasType.jcas.throwFeatMissing("possibilityTokens", "de.mpg.mpi.uima.type.MinIEModality");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEModality_Type)jcasType).casFeatCode_possibilityTokens), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEModality_Type)jcasType).casFeatCode_possibilityTokens), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: certaintyTokens

  /** getter for certaintyTokens - gets MinIE’s certaintyWords
   * @generated
   * @return value of the feature 
   */
  public FSArray getCertaintyTokens() {
    if (MinIEModality_Type.featOkTst && ((MinIEModality_Type)jcasType).casFeat_certaintyTokens == null)
      jcasType.jcas.throwFeatMissing("certaintyTokens", "de.mpg.mpi.uima.type.MinIEModality");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEModality_Type)jcasType).casFeatCode_certaintyTokens)));}
    
  /** setter for certaintyTokens - sets MinIE’s certaintyWords 
   * @generated
   * @param v value to set into the feature 
   */
  public void setCertaintyTokens(FSArray v) {
    if (MinIEModality_Type.featOkTst && ((MinIEModality_Type)jcasType).casFeat_certaintyTokens == null)
      jcasType.jcas.throwFeatMissing("certaintyTokens", "de.mpg.mpi.uima.type.MinIEModality");
    jcasType.ll_cas.ll_setRefValue(addr, ((MinIEModality_Type)jcasType).casFeatCode_certaintyTokens, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for certaintyTokens - gets an indexed value - MinIE’s certaintyWords
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public TOP getCertaintyTokens(int i) {
    if (MinIEModality_Type.featOkTst && ((MinIEModality_Type)jcasType).casFeat_certaintyTokens == null)
      jcasType.jcas.throwFeatMissing("certaintyTokens", "de.mpg.mpi.uima.type.MinIEModality");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEModality_Type)jcasType).casFeatCode_certaintyTokens), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEModality_Type)jcasType).casFeatCode_certaintyTokens), i)));}

  /** indexed setter for certaintyTokens - sets an indexed value - MinIE’s certaintyWords
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setCertaintyTokens(int i, TOP v) { 
    if (MinIEModality_Type.featOkTst && ((MinIEModality_Type)jcasType).casFeat_certaintyTokens == null)
      jcasType.jcas.throwFeatMissing("certaintyTokens", "de.mpg.mpi.uima.type.MinIEModality");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEModality_Type)jcasType).casFeatCode_certaintyTokens), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEModality_Type)jcasType).casFeatCode_certaintyTokens), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: certaintyType

  /** getter for certaintyType - gets 
   * @generated
   * @return value of the feature 
   */
  public String getCertaintyType() {
    if (MinIEModality_Type.featOkTst && ((MinIEModality_Type)jcasType).casFeat_certaintyType == null)
      jcasType.jcas.throwFeatMissing("certaintyType", "de.mpg.mpi.uima.type.MinIEModality");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MinIEModality_Type)jcasType).casFeatCode_certaintyType);}
    
  /** setter for certaintyType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCertaintyType(String v) {
    if (MinIEModality_Type.featOkTst && ((MinIEModality_Type)jcasType).casFeat_certaintyType == null)
      jcasType.jcas.throwFeatMissing("certaintyType", "de.mpg.mpi.uima.type.MinIEModality");
    jcasType.ll_cas.ll_setStringValue(addr, ((MinIEModality_Type)jcasType).casFeatCode_certaintyType, v);}    
  }

    