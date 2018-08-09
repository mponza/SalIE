

/* First created by JCasGen Thu Aug 09 14:11:06 CEST 2018 */
package de.mpg.mpi.uima.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.tcas.Annotation;


/** MinIE’s Polarity
 * Updated by JCasGen Thu Aug 09 16:07:33 CEST 2018
 * XML source: /Users/marcoponza/eclipse-workspace/UIMA/src/salieTypeSystemDescriptor.xml
 * @generated */
public class MinIEPolarity extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(MinIEPolarity.class);
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
  protected MinIEPolarity() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public MinIEPolarity(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public MinIEPolarity(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public MinIEPolarity(JCas jcas, int begin, int end) {
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
  //* Feature: negativeTokens

  /** getter for negativeTokens - gets MinIE’s negativeWords
   * @generated
   * @return value of the feature 
   */
  public FSArray getNegativeTokens() {
    if (MinIEPolarity_Type.featOkTst && ((MinIEPolarity_Type)jcasType).casFeat_negativeTokens == null)
      jcasType.jcas.throwFeatMissing("negativeTokens", "de.mpg.mpi.uima.type.MinIEPolarity");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEPolarity_Type)jcasType).casFeatCode_negativeTokens)));}
    
  /** setter for negativeTokens - sets MinIE’s negativeWords 
   * @generated
   * @param v value to set into the feature 
   */
  public void setNegativeTokens(FSArray v) {
    if (MinIEPolarity_Type.featOkTst && ((MinIEPolarity_Type)jcasType).casFeat_negativeTokens == null)
      jcasType.jcas.throwFeatMissing("negativeTokens", "de.mpg.mpi.uima.type.MinIEPolarity");
    jcasType.ll_cas.ll_setRefValue(addr, ((MinIEPolarity_Type)jcasType).casFeatCode_negativeTokens, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for negativeTokens - gets an indexed value - MinIE’s negativeWords
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public TOP getNegativeTokens(int i) {
    if (MinIEPolarity_Type.featOkTst && ((MinIEPolarity_Type)jcasType).casFeat_negativeTokens == null)
      jcasType.jcas.throwFeatMissing("negativeTokens", "de.mpg.mpi.uima.type.MinIEPolarity");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEPolarity_Type)jcasType).casFeatCode_negativeTokens), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEPolarity_Type)jcasType).casFeatCode_negativeTokens), i)));}

  /** indexed setter for negativeTokens - sets an indexed value - MinIE’s negativeWords
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setNegativeTokens(int i, TOP v) { 
    if (MinIEPolarity_Type.featOkTst && ((MinIEPolarity_Type)jcasType).casFeat_negativeTokens == null)
      jcasType.jcas.throwFeatMissing("negativeTokens", "de.mpg.mpi.uima.type.MinIEPolarity");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEPolarity_Type)jcasType).casFeatCode_negativeTokens), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEPolarity_Type)jcasType).casFeatCode_negativeTokens), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: polarityType

  /** getter for polarityType - gets Positive/Negative
   * @generated
   * @return value of the feature 
   */
  public String getPolarityType() {
    if (MinIEPolarity_Type.featOkTst && ((MinIEPolarity_Type)jcasType).casFeat_polarityType == null)
      jcasType.jcas.throwFeatMissing("polarityType", "de.mpg.mpi.uima.type.MinIEPolarity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MinIEPolarity_Type)jcasType).casFeatCode_polarityType);}
    
  /** setter for polarityType - sets Positive/Negative 
   * @generated
   * @param v value to set into the feature 
   */
  public void setPolarityType(String v) {
    if (MinIEPolarity_Type.featOkTst && ((MinIEPolarity_Type)jcasType).casFeat_polarityType == null)
      jcasType.jcas.throwFeatMissing("polarityType", "de.mpg.mpi.uima.type.MinIEPolarity");
    jcasType.ll_cas.ll_setStringValue(addr, ((MinIEPolarity_Type)jcasType).casFeatCode_polarityType, v);}    
  }

    