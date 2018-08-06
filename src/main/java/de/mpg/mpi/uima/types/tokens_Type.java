package de.mpg.mpi.uima.types;

/* First created by JCasGen Mon Aug 06 10:48:00 CEST 2018 */

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.cas.FSList_Type;

/** 
 * Updated by JCasGen Mon Aug 06 10:48:00 CEST 2018
 * @generated */
public class tokens_Type extends FSList_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = tokens.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("tokens");



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public tokens_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    