
/* First created by JCasGen Thu Aug 09 14:09:31 CEST 2018 */
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
public class MinIEQuantity_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = MinIEQuantity.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.mpg.mpi.uima.type.MinIEQuantity");
 
  /** @generated */
  final Feature casFeat_quantityTokens;
  /** @generated */
  final int     casFeatCode_quantityTokens;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getQuantityTokens(int addr) {
        if (featOkTst && casFeat_quantityTokens == null)
      jcas.throwFeatMissing("quantityTokens", "de.mpg.mpi.uima.type.MinIEQuantity");
    return ll_cas.ll_getRefValue(addr, casFeatCode_quantityTokens);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQuantityTokens(int addr, int v) {
        if (featOkTst && casFeat_quantityTokens == null)
      jcas.throwFeatMissing("quantityTokens", "de.mpg.mpi.uima.type.MinIEQuantity");
    ll_cas.ll_setRefValue(addr, casFeatCode_quantityTokens, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getQuantityTokens(int addr, int i) {
        if (featOkTst && casFeat_quantityTokens == null)
      jcas.throwFeatMissing("quantityTokens", "de.mpg.mpi.uima.type.MinIEQuantity");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_quantityTokens), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_quantityTokens), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_quantityTokens), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setQuantityTokens(int addr, int i, int v) {
        if (featOkTst && casFeat_quantityTokens == null)
      jcas.throwFeatMissing("quantityTokens", "de.mpg.mpi.uima.type.MinIEQuantity");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_quantityTokens), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_quantityTokens), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_quantityTokens), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_text;
  /** @generated */
  final int     casFeatCode_text;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "de.mpg.mpi.uima.type.MinIEQuantity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "de.mpg.mpi.uima.type.MinIEQuantity");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public MinIEQuantity_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_quantityTokens = jcas.getRequiredFeatureDE(casType, "quantityTokens", "uima.cas.FSArray", featOkTst);
    casFeatCode_quantityTokens  = (null == casFeat_quantityTokens) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_quantityTokens).getCode();

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

  }
}



    