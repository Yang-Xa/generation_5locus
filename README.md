# generation_5locus

The parameter: 
k-locus: to generate k-locus epistasis data, range: [1, 8], type: integer
PDR: the prevalence, default: 0.1, type: double
the error of PDR: according to the PDR, 0.05 is recommended, type: double
h2: heritability, range: [0.025, 0.4], 0.025 is recommended, type: double
the error of h2: according to heritability, it is recommended to set to h2 * 10-1, type: double
MAF: input k MAFs, separated by a space, range: [0.1, 0.4], type: double
SNPs: the total number of SNPs, type: integer
samples: the total number of samples, samples= diseased samples+ control samples, type: integer
diseased samples: the total number of diseased samples, type: integer
control samples: the total number of control samples, type: integer
path: the path where you save the generated data and the table of  and , type: string
model: three models, {model01, model02, model3}, type: string
