

/* First created by JCasGen Thu Aug 09 14:11:06 CEST 2018 */
package de.mpg.mpi.uima.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** 
 * Updated by JCasGen Thu Aug 09 16:07:33 CEST 2018
 * XML source: /Users/marcoponza/eclipse-workspace/UIMA/src/salieTypeSystemDescriptor.xml
 * @generated */
public class SalIEOpenFact extends OpenFact {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(SalIEOpenFact.class);
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
  protected SalIEOpenFact() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public SalIEOpenFact(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public SalIEOpenFact(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public SalIEOpenFact(JCas jcas, int begin, int end) {
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
  //* Feature: minieOpenFact

  /** getter for minieOpenFact - gets 
   * @generated
   * @return value of the feature 
   */
  public MinIEOpenFact getMinieOpenFact() {
    if (SalIEOpenFact_Type.featOkTst && ((SalIEOpenFact_Type)jcasType).casFeat_minieOpenFact == null)
      jcasType.jcas.throwFeatMissing("minieOpenFact", "de.mpg.mpi.uima.type.SalIEOpenFact");
    return (MinIEOpenFact)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SalIEOpenFact_Type)jcasType).casFeatCode_minieOpenFact)));}
    
  /** setter for minieOpenFact - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setMinieOpenFact(MinIEOpenFact v) {
    if (SalIEOpenFact_Type.featOkTst && ((SalIEOpenFact_Type)jcasType).casFeat_minieOpenFact == null)
      jcasType.jcas.throwFeatMissing("minieOpenFact", "de.mpg.mpi.uima.type.SalIEOpenFact");
    jcasType.ll_cas.ll_setRefValue(addr, ((SalIEOpenFact_Type)jcasType).casFeatCode_minieOpenFact, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: salience

  /** getter for salience - gets 
   * @generated
   * @return value of the feature 
   */
  public float getSalience() {
    if (SalIEOpenFact_Type.featOkTst && ((SalIEOpenFact_Type)jcasType).casFeat_salience == null)
      jcasType.jcas.throwFeatMissing("salience", "de.mpg.mpi.uima.type.SalIEOpenFact");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((SalIEOpenFact_Type)jcasType).casFeatCode_salience);}
    
  /** setter for salience - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSalience(float v) {
    if (SalIEOpenFact_Type.featOkTst && ((SalIEOpenFact_Type)jcasType).casFeat_salience == null)
      jcasType.jcas.throwFeatMissing("salience", "de.mpg.mpi.uima.type.SalIEOpenFact");
    jcasType.ll_cas.ll_setFloatValue(addr, ((SalIEOpenFact_Type)jcasType).casFeatCode_salience, v);}    
  }

    