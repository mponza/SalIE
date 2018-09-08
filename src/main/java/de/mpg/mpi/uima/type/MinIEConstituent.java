

/* First created by JCasGen Thu Aug 09 14:09:31 CEST 2018 */
package de.mpg.mpi.uima.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;


/** MinIE's AnnotatedPhrase
 * Updated by JCasGen Thu Aug 09 16:07:33 CEST 2018
 * XML source: /Users/marcoponza/eclipse-workspace/UIMA/src/salieTypeSystemDescriptor.xml
 * @generated */
public class MinIEConstituent extends Constituent {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(MinIEConstituent.class);
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
  protected MinIEConstituent() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public MinIEConstituent(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public MinIEConstituent(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public MinIEConstituent(JCas jcas, int begin, int end) {
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
  //* Feature: quantities

  /** getter for quantities - gets Array of MinIEQuantity
   * @generated
   * @return value of the feature 
   */
  public FSArray getQuantities() {
    if (MinIEConstituent_Type.featOkTst && ((MinIEConstituent_Type)jcasType).casFeat_quantities == null)
      jcasType.jcas.throwFeatMissing("quantities", "de.mpg.mpi.uima.type.MinIEConstituent");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEConstituent_Type)jcasType).casFeatCode_quantities)));}
    
  /** setter for quantities - sets Array of MinIEQuantity 
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuantities(FSArray v) {
    if (MinIEConstituent_Type.featOkTst && ((MinIEConstituent_Type)jcasType).casFeat_quantities == null)
      jcasType.jcas.throwFeatMissing("quantities", "de.mpg.mpi.uima.type.MinIEConstituent");
    jcasType.ll_cas.ll_setRefValue(addr, ((MinIEConstituent_Type)jcasType).casFeatCode_quantities, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for quantities - gets an indexed value - Array of MinIEQuantity
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public TOP getQuantities(int i) {
    if (MinIEConstituent_Type.featOkTst && ((MinIEConstituent_Type)jcasType).casFeat_quantities == null)
      jcasType.jcas.throwFeatMissing("quantities", "de.mpg.mpi.uima.type.MinIEConstituent");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEConstituent_Type)jcasType).casFeatCode_quantities), i);
    return (TOP)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEConstituent_Type)jcasType).casFeatCode_quantities), i)));}

  /** indexed setter for quantities - sets an indexed value - Array of MinIEQuantity
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setQuantities(int i, TOP v) { 
    if (MinIEConstituent_Type.featOkTst && ((MinIEConstituent_Type)jcasType).casFeat_quantities == null)
      jcasType.jcas.throwFeatMissing("quantities", "de.mpg.mpi.uima.type.MinIEConstituent");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEConstituent_Type)jcasType).casFeatCode_quantities), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((MinIEConstituent_Type)jcasType).casFeatCode_quantities), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    