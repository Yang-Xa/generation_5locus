# generation_5locus (proDMEn)

### The parameter:  
***k-locus***: to generate k-locus epistasis data, range: [1, 8], type: integer  
***PDR***: the prevalence, default: 0.1, type: double  
***the bias of PDR***: according to the PDR, 0.05 is recommended, type: double  
***heritability***: range: [0.025, 0.4], 0.025 is recommended, type: double  
***the bias of heritability***: according to heritability, it is recommended to set to h2 * 10-1, type: double  
***MAF***: input k MAFs, separated by a space, range: [0.1, 0.4], type: double  
***SNPs***: the total number of SNPs, type: integer  
***samples***: the total number of samples, samples= diseased samples+ control samples, type: integer  
***diseased samples***: the total number of diseased samples, type: integer  
***control samples***: the total number of control samples, type: integer  
***path1***: the path where you save the table of alpha and beta, including the filename, type: string  
***path2***: the path where you save the generated data, including the filename, type: string  
***model***: three models, {model01, model02, model3}, type: string   


### Example:

![res](https://user-images.githubusercontent.com/26017665/115489857-7f052a00-a28f-11eb-8fa5-f6b82e681a82.PNG)
