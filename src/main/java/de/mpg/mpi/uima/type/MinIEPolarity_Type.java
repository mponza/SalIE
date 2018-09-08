
/* First created by JCasGen Thu Aug 09 14:11:06 CEST 2018 */
package de.mpg.mpi.uima.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** MinIE's Polarity
 * Updated by JCasGen Thu Aug 09 16:07:33 CEST 2018
 * @generated */
public class MinIEPolarity_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = MinIEPolarity.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.mpg.mpi.uima.type.MinIEPolarity");
 
  /** @generated */
  final Feature casFeat_negativeTokens;
  /** @generated */
  final int     casFeatCode_negativeTokens;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getNegativeTokens(int addr) {
        if (featOkTst && casFeat_negativeTokens == null)
      jcas.throwFeatMissing("negativeTokens", "de.mpg.mpi.uima.type.MinIEPolarity");
    return ll_cas.ll_getRefValue(addr, casFeatCode_negativeTokens);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setNegativeTokens(int addr, int v) {
        if (featOkTst && casFeat_negativeTokens == null)
      jcas.throwFeatMissing("negativeTokens", "de.mpg.mpi.uima.type.MinIEPolarity");
    ll_cas.ll_setRefValue(addr, casFeatCode_negativeTokens, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getNegativeTokens(int addr, int i) {
        if (featOkTst && casFeat_negativeTokens == null)
      jcas.throwFeatMissing("negativeTokens", "de.mpg.mpi.uima.type.MinIEPolarity");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_negativeTokens), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_negativeTokens), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_negativeTokens), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setNegativeTokens(int addr, int i, int v) {
        if (featOkTst && casFeat_negativeTokens == null)
      jcas.throwFeatMissing("negativeTokens", "de.mpg.mpi.uima.type.MinIEPolarity");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_negativeTokens), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_negativeTokens), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_negativeTokens), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_polarityType;
  /** @generated */
  final int     casFeatCode_polarityType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getPolarityType(int addr) {
        if (featOkTst && casFeat_polarityType == null)
      jcas.throwFeatMissing("polarityType", "de.mpg.mpi.uima.type.MinIEPolarity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_polarityType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPolarityType(int addr, String v) {
        if (featOkTst && casFeat_polarityType == null)
      jcas.throwFeatMissing("polarityType", "de.mpg.mpi.uima.type.MinIEPolarity");
    ll_cas.ll_setStringValue(addr, casFeatCode_polarityType, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public MinIEPolarity_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_negativeTokens = jcas.getRequiredFeatureDE(casType, "negativeTokens", "uima.cas.FSArray", featOkTst);
    casFeatCode_negativeTokens  = (null == casFeat_negativeTokens) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_negativeTokens).getCode();

 
    casFeat_polarityType = jcas.getRequiredFeatureDE(casType, "polarityType", "uima.cas.String", featOkTst);
    casFeatCode_polarityType  = (null == casFeat_polarityType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_polarityType).getCode();

  }
}



    