package de.mpg.mpi.uima.types;

/* First created by JCasGen Mon Aug 06 17:34:56 CEST 2018 */

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** 
 * Updated by JCasGen Tue Aug 07 11:49:19 CEST 2018
 * @generated */
public class MinIEOpenFact_Type extends OpenFact_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = MinIEOpenFact.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("MinIEOpenFact");
 
  /** @generated */
  final Feature casFeat_polarity;
  /** @generated */
  final int     casFeatCode_polarity;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getPolarity(int addr) {
        if (featOkTst && casFeat_polarity == null)
      jcas.throwFeatMissing("polarity", "MinIEOpenFact");
    return ll_cas.ll_getStringValue(addr, casFeatCode_polarity);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPolarity(int addr, String v) {
        if (featOkTst && casFeat_polarity == null)
      jcas.throwFeatMissing("polarity", "MinIEOpenFact");
    ll_cas.ll_setStringValue(addr, casFeatCode_polarity, v);}
    
  
 
  /** @generated */
  final Feature casFeat_modality;
  /** @generated */
  final int     casFeatCode_modality;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getModality(int addr) {
        if (featOkTst && casFeat_modality == null)
      jcas.throwFeatMissing("modality", "MinIEOpenFact");
    return ll_cas.ll_getStringValue(addr, casFeatCode_modality);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setModality(int addr, String v) {
        if (featOkTst && casFeat_modality == null)
      jcas.throwFeatMissing("modality", "MinIEOpenFact");
    ll_cas.ll_setStringValue(addr, casFeatCode_modality, v);}
    
  
 
  /** @generated */
  final Feature casFeat_attribution;
  /** @generated */
  final int     casFeatCode_attribution;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getAttribution(int addr) {
        if (featOkTst && casFeat_attribution == null)
      jcas.throwFeatMissing("attribution", "MinIEOpenFact");
    return ll_cas.ll_getStringValue(addr, casFeatCode_attribution);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAttribution(int addr, String v) {
        if (featOkTst && casFeat_attribution == null)
      jcas.throwFeatMissing("attribution", "MinIEOpenFact");
    ll_cas.ll_setStringValue(addr, casFeatCode_attribution, v);}
    
  
 
  /** @generated */
  final Feature casFeat_quantity;
  /** @generated */
  final int     casFeatCode_quantity;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getQuantity(int addr) {
        if (featOkTst && casFeat_quantity == null)
      jcas.throwFeatMissing("quantity", "MinIEOpenFact");
    return ll_cas.ll_getStringValue(addr, casFeatCode_quantity);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQuantity(int addr, String v) {
        if (featOkTst && casFeat_quantity == null)
      jcas.throwFeatMissing("quantity", "MinIEOpenFact");
    ll_cas.ll_setStringValue(addr, casFeatCode_quantity, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
   * @generated
   * @param jcas JCas
   * @param casType Type 
   */
  public MinIEOpenFact_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_polarity = jcas.getRequiredFeatureDE(casType, "polarity", "uima.cas.String", featOkTst);
    casFeatCode_polarity  = (null == casFeat_polarity) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_polarity).getCode();

 
    casFeat_modality = jcas.getRequiredFeatureDE(casType, "modality", "uima.cas.String", featOkTst);
    casFeatCode_modality  = (null == casFeat_modality) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_modality).getCode();

 
    casFeat_attribution = jcas.getRequiredFeatureDE(casType, "attribution", "uima.cas.String", featOkTst);
    casFeatCode_attribution  = (null == casFeat_attribution) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_attribution).getCode();

 
    casFeat_quantity = jcas.getRequiredFeatureDE(casType, "quantity", "uima.cas.String", featOkTst);
    casFeatCode_quantity  = (null == casFeat_quantity) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_quantity).getCode();

  }
}



    