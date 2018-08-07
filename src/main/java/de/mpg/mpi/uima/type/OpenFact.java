

/* First created by JCasGen Tue Aug 07 17:11:25 CEST 2018 */
package de.mpg.mpi.uima.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Aug 07 17:14:20 CEST 2018
 * XML source: /Users/marcoponza/eclipse-workspace/UIMA/openFactTypeSystemDescriptor.xml
 * @generated */
public class OpenFact extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(OpenFact.class);
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
  protected OpenFact() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public OpenFact(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public OpenFact(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public OpenFact(JCas jcas, int begin, int end) {
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
  //* Feature: subject

  /** getter for subject - gets 
   * @generated
   * @return value of the feature 
   */
  public Constituent getSubject() {
    if (OpenFact_Type.featOkTst && ((OpenFact_Type)jcasType).casFeat_subject == null)
      jcasType.jcas.throwFeatMissing("subject", "de.mpg.mpi.uima.type.OpenFact");
    return (Constituent)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((OpenFact_Type)jcasType).casFeatCode_subject)));}
    
  /** setter for subject - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSubject(Constituent v) {
    if (OpenFact_Type.featOkTst && ((OpenFact_Type)jcasType).casFeat_subject == null)
      jcasType.jcas.throwFeatMissing("subject", "de.mpg.mpi.uima.type.OpenFact");
    jcasType.ll_cas.ll_setRefValue(addr, ((OpenFact_Type)jcasType).casFeatCode_subject, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: relation

  /** getter for relation - gets 
   * @generated
   * @return value of the feature 
   */
  public Constituent getRelation() {
    if (OpenFact_Type.featOkTst && ((OpenFact_Type)jcasType).casFeat_relation == null)
      jcasType.jcas.throwFeatMissing("relation", "de.mpg.mpi.uima.type.OpenFact");
    return (Constituent)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((OpenFact_Type)jcasType).casFeatCode_relation)));}
    
  /** setter for relation - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setRelation(Constituent v) {
    if (OpenFact_Type.featOkTst && ((OpenFact_Type)jcasType).casFeat_relation == null)
      jcasType.jcas.throwFeatMissing("relation", "de.mpg.mpi.uima.type.OpenFact");
    jcasType.ll_cas.ll_setRefValue(addr, ((OpenFact_Type)jcasType).casFeatCode_relation, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: object

  /** getter for object - gets 
   * @generated
   * @return value of the feature 
   */
  public Constituent getObject() {
    if (OpenFact_Type.featOkTst && ((OpenFact_Type)jcasType).casFeat_object == null)
      jcasType.jcas.throwFeatMissing("object", "de.mpg.mpi.uima.type.OpenFact");
    return (Constituent)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((OpenFact_Type)jcasType).casFeatCode_object)));}
    
  /** setter for object - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setObject(Constituent v) {
    if (OpenFact_Type.featOkTst && ((OpenFact_Type)jcasType).casFeat_object == null)
      jcasType.jcas.throwFeatMissing("object", "de.mpg.mpi.uima.type.OpenFact");
    jcasType.ll_cas.ll_setRefValue(addr, ((OpenFact_Type)jcasType).casFeatCode_object, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets 
   * @generated
   * @return value of the feature 
   */
  public String getText() {
    if (OpenFact_Type.featOkTst && ((OpenFact_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "de.mpg.mpi.uima.type.OpenFact");
    return jcasType.ll_cas.ll_getStringValue(addr, ((OpenFact_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (OpenFact_Type.featOkTst && ((OpenFact_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "de.mpg.mpi.uima.type.OpenFact");
    jcasType.ll_cas.ll_setStringValue(addr, ((OpenFact_Type)jcasType).casFeatCode_text, v);}    
  }

    