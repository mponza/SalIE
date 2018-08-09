
/* First created by JCasGen Tue Aug 07 17:11:25 CEST 2018 */
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
public class MinIEOpenFact_Type extends OpenFact_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = MinIEOpenFact.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.mpg.mpi.uima.type.MinIEOpenFact");
 
  /** @generated */
  final Feature casFeat_attribution;
  /** @generated */
  final int     casFeatCode_attribution;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getAttribution(int addr) {
        if (featOkTst && casFeat_attribution == null)
      jcas.throwFeatMissing("attribution", "de.mpg.mpi.uima.type.MinIEOpenFact");
    return ll_cas.ll_getRefValue(addr, casFeatCode_attribution);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAttribution(int addr, int v) {
        if (featOkTst && casFeat_attribution == null)
      jcas.throwFeatMissing("attribution", "de.mpg.mpi.uima.type.MinIEOpenFact");
    ll_cas.ll_setRefValue(addr, casFeatCode_attribution, v);}
    
  
 
  /** @generated */
  final Feature casFeat_polarity;
  /** @generated */
  final int     casFeatCode_polarity;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getPolarity(int addr) {
        if (featOkTst && casFeat_polarity == null)
      jcas.throwFeatMissing("polarity", "de.mpg.mpi.uima.type.MinIEOpenFact");
    return ll_cas.ll_getRefValue(addr, casFeatCode_polarity);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPolarity(int addr, int v) {
        if (featOkTst && casFeat_polarity == null)
      jcas.throwFeatMissing("polarity", "de.mpg.mpi.uima.type.MinIEOpenFact");
    ll_cas.ll_setRefValue(addr, casFeatCode_polarity, v);}
    
  
 
  /** @generated */
  final Feature casFeat_modality;
  /** @generated */
  final int     casFeatCode_modality;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getModality(int addr) {
        if (featOkTst && casFeat_modality == null)
      jcas.throwFeatMissing("modality", "de.mpg.mpi.uima.type.MinIEOpenFact");
    return ll_cas.ll_getRefValue(addr, casFeatCode_modality);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setModality(int addr, int v) {
        if (featOkTst && casFeat_modality == null)
      jcas.throwFeatMissing("modality", "de.mpg.mpi.uima.type.MinIEOpenFact");
    ll_cas.ll_setRefValue(addr, casFeatCode_modality, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public MinIEOpenFact_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_attribution = jcas.getRequiredFeatureDE(casType, "attribution", "de.mpg.mpi.uima.type.MinIEAttribution", featOkTst);
    casFeatCode_attribution  = (null == casFeat_attribution) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_attribution).getCode();

 
    casFeat_polarity = jcas.getRequiredFeatureDE(casType, "polarity", "de.mpg.mpi.uima.type.MinIEPolarity", featOkTst);
    casFeatCode_polarity  = (null == casFeat_polarity) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_polarity).getCode();

 
    casFeat_modality = jcas.getRequiredFeatureDE(casType, "modality", "de.mpg.mpi.uima.type.MinIEModality", featOkTst);
    casFeatCode_modality  = (null == casFeat_modality) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_modality).getCode();

  }
}



    