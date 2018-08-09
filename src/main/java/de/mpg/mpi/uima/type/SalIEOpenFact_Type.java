
/* First created by JCasGen Thu Aug 09 14:11:06 CEST 2018 */
package de.mpg.mpi.uima.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** 
 * Updated by JCasGen Thu Aug 09 16:07:33 CEST 2018
 * @generated */
public class SalIEOpenFact_Type extends OpenFact_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = SalIEOpenFact.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.mpg.mpi.uima.type.SalIEOpenFact");
 
  /** @generated */
  final Feature casFeat_minieOpenFact;
  /** @generated */
  final int     casFeatCode_minieOpenFact;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getMinieOpenFact(int addr) {
        if (featOkTst && casFeat_minieOpenFact == null)
      jcas.throwFeatMissing("minieOpenFact", "de.mpg.mpi.uima.type.SalIEOpenFact");
    return ll_cas.ll_getRefValue(addr, casFeatCode_minieOpenFact);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setMinieOpenFact(int addr, int v) {
        if (featOkTst && casFeat_minieOpenFact == null)
      jcas.throwFeatMissing("minieOpenFact", "de.mpg.mpi.uima.type.SalIEOpenFact");
    ll_cas.ll_setRefValue(addr, casFeatCode_minieOpenFact, v);}
    
  
 
  /** @generated */
  final Feature casFeat_salience;
  /** @generated */
  final int     casFeatCode_salience;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public float getSalience(int addr) {
        if (featOkTst && casFeat_salience == null)
      jcas.throwFeatMissing("salience", "de.mpg.mpi.uima.type.SalIEOpenFact");
    return ll_cas.ll_getFloatValue(addr, casFeatCode_salience);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSalience(int addr, float v) {
        if (featOkTst && casFeat_salience == null)
      jcas.throwFeatMissing("salience", "de.mpg.mpi.uima.type.SalIEOpenFact");
    ll_cas.ll_setFloatValue(addr, casFeatCode_salience, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public SalIEOpenFact_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_minieOpenFact = jcas.getRequiredFeatureDE(casType, "minieOpenFact", "de.mpg.mpi.uima.type.MinIEOpenFact", featOkTst);
    casFeatCode_minieOpenFact  = (null == casFeat_minieOpenFact) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_minieOpenFact).getCode();

 
    casFeat_salience = jcas.getRequiredFeatureDE(casType, "salience", "uima.cas.Float", featOkTst);
    casFeatCode_salience  = (null == casFeat_salience) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_salience).getCode();

  }
}



    