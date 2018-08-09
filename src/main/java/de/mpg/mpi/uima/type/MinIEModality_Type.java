
/* First created by JCasGen Thu Aug 09 14:11:06 CEST 2018 */
package de.mpg.mpi.uima.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Thu Aug 09 16:07:33 CEST 2018
 * @generated */
public class MinIEModality_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = MinIEModality.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.mpg.mpi.uima.type.MinIEModality");
 
  /** @generated */
  final Feature casFeat_possibilityTokens;
  /** @generated */
  final int     casFeatCode_possibilityTokens;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getPossibilityTokens(int addr) {
        if (featOkTst && casFeat_possibilityTokens == null)
      jcas.throwFeatMissing("possibilityTokens", "de.mpg.mpi.uima.type.MinIEModality");
    return ll_cas.ll_getRefValue(addr, casFeatCode_possibilityTokens);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPossibilityTokens(int addr, int v) {
        if (featOkTst && casFeat_possibilityTokens == null)
      jcas.throwFeatMissing("possibilityTokens", "de.mpg.mpi.uima.type.MinIEModality");
    ll_cas.ll_setRefValue(addr, casFeatCode_possibilityTokens, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getPossibilityTokens(int addr, int i) {
        if (featOkTst && casFeat_possibilityTokens == null)
      jcas.throwFeatMissing("possibilityTokens", "de.mpg.mpi.uima.type.MinIEModality");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_possibilityTokens), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_possibilityTokens), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_possibilityTokens), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setPossibilityTokens(int addr, int i, int v) {
        if (featOkTst && casFeat_possibilityTokens == null)
      jcas.throwFeatMissing("possibilityTokens", "de.mpg.mpi.uima.type.MinIEModality");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_possibilityTokens), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_possibilityTokens), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_possibilityTokens), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_certaintyTokens;
  /** @generated */
  final int     casFeatCode_certaintyTokens;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getCertaintyTokens(int addr) {
        if (featOkTst && casFeat_certaintyTokens == null)
      jcas.throwFeatMissing("certaintyTokens", "de.mpg.mpi.uima.type.MinIEModality");
    return ll_cas.ll_getRefValue(addr, casFeatCode_certaintyTokens);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCertaintyTokens(int addr, int v) {
        if (featOkTst && casFeat_certaintyTokens == null)
      jcas.throwFeatMissing("certaintyTokens", "de.mpg.mpi.uima.type.MinIEModality");
    ll_cas.ll_setRefValue(addr, casFeatCode_certaintyTokens, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getCertaintyTokens(int addr, int i) {
        if (featOkTst && casFeat_certaintyTokens == null)
      jcas.throwFeatMissing("certaintyTokens", "de.mpg.mpi.uima.type.MinIEModality");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_certaintyTokens), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_certaintyTokens), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_certaintyTokens), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setCertaintyTokens(int addr, int i, int v) {
        if (featOkTst && casFeat_certaintyTokens == null)
      jcas.throwFeatMissing("certaintyTokens", "de.mpg.mpi.uima.type.MinIEModality");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_certaintyTokens), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_certaintyTokens), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_certaintyTokens), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_certaintyType;
  /** @generated */
  final int     casFeatCode_certaintyType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getCertaintyType(int addr) {
        if (featOkTst && casFeat_certaintyType == null)
      jcas.throwFeatMissing("certaintyType", "de.mpg.mpi.uima.type.MinIEModality");
    return ll_cas.ll_getStringValue(addr, casFeatCode_certaintyType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCertaintyType(int addr, String v) {
        if (featOkTst && casFeat_certaintyType == null)
      jcas.throwFeatMissing("certaintyType", "de.mpg.mpi.uima.type.MinIEModality");
    ll_cas.ll_setStringValue(addr, casFeatCode_certaintyType, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public MinIEModality_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_possibilityTokens = jcas.getRequiredFeatureDE(casType, "possibilityTokens", "uima.cas.FSArray", featOkTst);
    casFeatCode_possibilityTokens  = (null == casFeat_possibilityTokens) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_possibilityTokens).getCode();

 
    casFeat_certaintyTokens = jcas.getRequiredFeatureDE(casType, "certaintyTokens", "uima.cas.FSArray", featOkTst);
    casFeatCode_certaintyTokens  = (null == casFeat_certaintyTokens) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_certaintyTokens).getCode();

 
    casFeat_certaintyType = jcas.getRequiredFeatureDE(casType, "certaintyType", "uima.cas.String", featOkTst);
    casFeatCode_certaintyType  = (null == casFeat_certaintyType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_certaintyType).getCode();

  }
}



    