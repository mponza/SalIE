
/* First created by JCasGen Thu Aug 09 14:09:31 CEST 2018 */
package de.mpg.mpi.uima.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** MinIE’s AnnotatedPhrase
 * Updated by JCasGen Thu Aug 09 16:07:33 CEST 2018
 * @generated */
public class MinIEConstituent_Type extends Constituent_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = MinIEConstituent.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.mpg.mpi.uima.type.MinIEConstituent");
 
  /** @generated */
  final Feature casFeat_quantities;
  /** @generated */
  final int     casFeatCode_quantities;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getQuantities(int addr) {
        if (featOkTst && casFeat_quantities == null)
      jcas.throwFeatMissing("quantities", "de.mpg.mpi.uima.type.MinIEConstituent");
    return ll_cas.ll_getRefValue(addr, casFeatCode_quantities);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQuantities(int addr, int v) {
        if (featOkTst && casFeat_quantities == null)
      jcas.throwFeatMissing("quantities", "de.mpg.mpi.uima.type.MinIEConstituent");
    ll_cas.ll_setRefValue(addr, casFeatCode_quantities, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public int getQuantities(int addr, int i) {
        if (featOkTst && casFeat_quantities == null)
      jcas.throwFeatMissing("quantities", "de.mpg.mpi.uima.type.MinIEConstituent");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_quantities), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_quantities), i);
  return ll_cas.ll_getRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_quantities), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setQuantities(int addr, int i, int v) {
        if (featOkTst && casFeat_quantities == null)
      jcas.throwFeatMissing("quantities", "de.mpg.mpi.uima.type.MinIEConstituent");
    if (lowLevelTypeChecks)
      ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_quantities), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_quantities), i);
    ll_cas.ll_setRefArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_quantities), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public MinIEConstituent_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_quantities = jcas.getRequiredFeatureDE(casType, "quantities", "uima.cas.FSArray", featOkTst);
    casFeatCode_quantities  = (null == casFeat_quantities) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_quantities).getCode();

  }
}



    