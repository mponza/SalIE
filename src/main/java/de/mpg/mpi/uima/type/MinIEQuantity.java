

/* First created by JCasGen Thu Aug 09 14:09:31 CEST 2018 */
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
public class MinIEQuantity extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(MinIEQuantity.class);
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
  protected MinIEQuantity() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public MinIEQuantity(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public MinIEQuantity(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public MinIEQuantity(JCas jcas, int begin, int end) {
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
  //* Feature: quantityTokens

  /** getter for quantityTokens - gets MinIE's qWords
   * @generated
   * @return value of the feature 
   */
  public FSArray getQuantityTokens() {
    if (MinIEQuantity_Type.featOkTst && ((MinIEQuantity_Type)jcasType).casFeat_quantityTokens == null)
      jcasType.jcas.throwFeatMissing("quantityTokens", "de.mpg.mpi.uima.type.MinIEQuantity");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEQuantity_Type)jcasType).casFeatCode_quantityTokens)));}
    
  /** setter for quantityTokens - sets MinIE's qWords 
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuantityTokens(FSArray v) {
    if (MinIEQuantity_Type.featOkTst && ((MinIEQuantity_Type)jcasType).casFeat_quantityTokens == null)
      jcasType.jcas.throwFeatMissing("quantityTokens", "de.mpg.mpi.uima.type.MinIEQuantity");
    jcasType.ll_cas.ll_setRefValue(addr, ((MinIEQuantity_Type)jcasType).casFeatCode_quantityTokens, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for quantityTokens - gets an indexed value - MinIE's qWords
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public TOP getQuantityTokens(int i) {
    if (MinIEQuantity_Type.featOkTst && ((MinIEQuantity_Type)jcasType).casFeat_quantityTokens == null)
      jcasType.jcas.throwFeatMissing("quantityTokens", "de.mpg.mpi.uima.type.MinIEQuantity");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEQuantity_Type)jcasType).casFeatCode_quantityTokens), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEQuantity_Type)jcasType).casFeatCode_quantityTokens), i)));}

  /** indexed setter for quantityTokens - sets an indexed value - MinIE's qWords
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setQuantityTokens(int i, TOP v) { 
    if (MinIEQuantity_Type.featOkTst && ((MinIEQuantity_Type)jcasType).casFeat_quantityTokens == null)
      jcasType.jcas.throwFeatMissing("quantityTokens", "de.mpg.mpi.uima.type.MinIEQuantity");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEQuantity_Type)jcasType).casFeatCode_quantityTokens), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEQuantity_Type)jcasType).casFeatCode_quantityTokens), i, jcasType.ll_cas.ll_getFSRef(v));}
   
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets 
   * @generated
   * @return value of the feature 
   */
  public String getText() {
    if (MinIEQuantity_Type.featOkTst && ((MinIEQuantity_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "de.mpg.mpi.uima.type.MinIEQuantity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((MinIEQuantity_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (MinIEQuantity_Type.featOkTst && ((MinIEQuantity_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "de.mpg.mpi.uima.type.MinIEQuantity");
    jcasType.ll_cas.ll_setStringValue(addr, ((MinIEQuantity_Type)jcasType).casFeatCode_text, v);}    
  }

    