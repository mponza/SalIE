
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
public class MinIEAttribution_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = MinIEAttribution.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.mpg.mpi.uima.type.MinIEAttribution");
 
  /** @generated */
  final Feature casFeat_attributionConstituent;
  /** @generated */
  final int     casFeatCode_attributionConstituent;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getAttributionConstituent(int addr) {
        if (featOkTst && casFeat_attributionConstituent == null)
      jcas.throwFeatMissing("attributionConstituent", "de.mpg.mpi.uima.type.MinIEAttribution");
    return ll_cas.ll_getRefValue(addr, casFeatCode_attributionConstituent);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAttributionConstituent(int addr, int v) {
        if (featOkTst && casFeat_attributionConstituent == null)
      jcas.throwFeatMissing("attributionConstituent", "de.mpg.mpi.uima.type.MinIEAttribution");
    ll_cas.ll_setRefValue(addr, casFeatCode_attributionConstituent, v);}
    
  
 
  /** @generated */
  final Feature casFeat_modalityType;
  /** @generated */
  final int     casFeatCode_modalityType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getModalityType(int addr) {
        if (featOkTst && casFeat_modalityType == null)
      jcas.throwFeatMissing("modalityType", "de.mpg.mpi.uima.type.MinIEAttribution");
    return ll_cas.ll_getStringValue(addr, casFeatCode_modalityType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setModalityType(int addr, String v) {
        if (featOkTst && casFeat_modalityType == null)
      jcas.throwFeatMissing("modalityType", "de.mpg.mpi.uima.type.MinIEAttribution");
    ll_cas.ll_setStringValue(addr, casFeatCode_modalityType, v);}
    
  
 
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
      jcas.throwFeatMissing("polarityType", "de.mpg.mpi.uima.type.MinIEAttribution");
    return ll_cas.ll_getStringValue(addr, casFeatCode_polarityType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setPolarityType(int addr, String v) {
        if (featOkTst && casFeat_polarityType == null)
      jcas.throwFeatMissing("polarityType", "de.mpg.mpi.uima.type.MinIEAttribution");
    ll_cas.ll_setStringValue(addr, casFeatCode_polarityType, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public MinIEAttribution_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_attributionConstituent = jcas.getRequiredFeatureDE(casType, "attributionConstituent", "de.mpg.mpi.uima.type.Constituent", featOkTst);
    casFeatCode_attributionConstituent  = (null == casFeat_attributionConstituent) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_attributionConstituent).getCode();

 
    casFeat_modalityType = jcas.getRequiredFeatureDE(casType, "modalityType", "uima.cas.String", featOkTst);
    casFeatCode_modalityType  = (null == casFeat_modalityType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_modalityType).getCode();

 
    casFeat_polarityType = jcas.getRequiredFeatureDE(casType, "polarityType", "uima.cas.String", featOkTst);
    casFeatCode_polarityType  = (null == casFeat_polarityType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_polarityType).getCode();

  }
}



    