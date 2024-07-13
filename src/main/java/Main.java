import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.fusesource.jansi.AnsiConsole;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static void main(String[] args) {
		AnsiConsole.systemInstall();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Seleccione una opción:");
		System.out.println("1. Utilizar un único punto de venta");
		System.out.println("2. Utilizar múltiples puntos de venta");
		int opcion = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		String filePath = "subdiarioventas.xlsx"; // Reemplaza con la ruta a tu archivo Excel

		if (opcion == 1) {
			procesarArchivoExcel(filePath);
		} else if (opcion == 2) {
			procesarArchivoPorPuntoDeVenta(filePath);
		} else {
			System.out.println("Opción no válida.");
		}
	}

	public static void procesarArchivoExcel(String filePath) {
		// Declaraciones Total Neto Gravado
		double totalFacturaAResponsableInscriptoPositivos = 0.0;
		double totalFacturaAResponsableInscriptoNegativos = 0.0;
		double totalFacturaAResponsableInscripto = 0.0;
		double totalFacturaAExentosNoAlcanzadosPositivos = 0.0;
		double totalFacturaAExentosNoAlcanzadosNegativos = 0.0;
		double totalFacturaAExentosNoAlcanzados = 0.0;
		double totalFacturaAMonotributistaPositivos = 0.0;
		double totalFacturaAMonotributistaNegativos = 0.0;
		double totalFacturaAMonotributista = 0.0;
		double totalFacturaAPositivos = 0.0;
		double totalFacturaANegativos = 0.0;
		double totalFacturaA = 0.0;
		double totalFacturaBConsumidorFinalPositivos = 0.0;
		double totalFacturaBConsumidorFinalNegativos = 0.0;
		double totalFacturaBConsumidorFinal = 0.0;
		double totalFacturaBMonotributistaPositivos = 0.0;
		double totalFacturaBMonotributistaNegativos = 0.0;
		double totalFacturaBMonotributista = 0.0;
		double totalFacturaBExentosNoAlcanzadosPositivos = 0.0;
		double totalFacturaBExentosNoAlcanzadosNegativos = 0.0;
		double totalFacturaBExentosNoAlcanzados = 0.0;
		double totalFacturaBPositivos = 0.0;
		double totalFacturaBNegativos = 0.0;
		double totalFacturaB = 0.0;
		double totalFacturaZ_A_Positivos = 0.0;
		double totalFacturaZ_A_Negativos = 0.0;
		double totalFacturaZ_A = 0.0;
		double totalFacturaZ_B_Positivos = 0.0;
		double totalFacturaZ_B_Negativos = 0.0;
		double totalFacturaZ_B = 0.0;
		double totalFacturaZPositivos = 0.0;
		double totalFacturaZNegativos = 0.0;
		double totalFacturaZ = 0.0;
		double totalFacturaAPositivosFacturaZ_A_Positivos = 0.0;
		double totalFacturaANegativosFacturaZ_A_Negativos = 0.0;
		double totalFacturaAFacturaZ_A = 0.0;
		double totalFacturaBPositivosFacturaZ_B_Positivos = 0.0;
		double totalFacturaBNegativosFacturaZ_B_Negativos = 0.0;
		double totalFacturaBFacturaZ_B = 0.0;
		double totalFacturaZ_A_Positivos_Monotributistas = 0.0;
		double totalFacturaZ_B_Positivos_Monotributistas = 0.0;
		double totalFacturaZ_B_Positivos_ConsumidoresFinales = 0.0;
		double totalFacturaZ_A_Positivos_ExentosNoAlcanzados = 0.0;
		double totalFacturaZ_B_Positivos_ExentosNoAlcanzados = 0.0;
		double totalFacturaZ_A_Positivos_ResponsableInscripto = 0.0;

		// Declaraciones Total Iva
		double totalFacturaAResponsableInscriptoPositivosIva = 0.0;
		double totalFacturaAResponsableInscriptoNegativosIva = 0.0;
		double totalFacturaAResponsableInscriptoIva = 0.0;
		double totalFacturaAExentosNoAlcanzadosPositivosIva = 0.0;
		double totalFacturaAExentosNoAlcanzadosNegativosIva = 0.0;
		double totalFacturaAExentosNoAlcanzadosIva = 0.0;
		double totalFacturaAMonotributistaPositivosIva = 0.0;
		double totalFacturaAMonotributistaNegativosIva = 0.0;
		double totalFacturaAMonotributistaIva = 0.0;
		double totalFacturaAPositivosIva = 0.0;
		double totalFacturaANegativosIva = 0.0;
		double totalFacturaAIva = 0.0;
		double totalFacturaBConsumidorFinalPositivosIva = 0.0;
		double totalFacturaBConsumidorFinalNegativosIva = 0.0;
		double totalFacturaBConsumidorFinalIva = 0.0;
		double totalFacturaBMonotributistaPositivosIva = 0.0;
		double totalFacturaBMonotributistaNegativosIva = 0.0;
		double totalFacturaBMonotributistaIva = 0.0;
		double totalFacturaBExentosNoAlcanzadosPositivosIva = 0.0;
		double totalFacturaBExentosNoAlcanzadosNegativosIva = 0.0;
		double totalFacturaBExentosNoAlcanzadosIva = 0.0;
		double totalFacturaBPositivosIva = 0.0;
		double totalFacturaBNegativosIva = 0.0;
		double totalFacturaBIva = 0.0;
		double totalFacturaZ_A_PositivosIva = 0.0;
		double totalFacturaZ_A_NegativosIva = 0.0;
		double totalFacturaZ_A_Iva = 0.0;
		double totalFacturaZ_B_PositivosIva = 0.0;
		double totalFacturaZ_B_NegativosIva = 0.0;
		double totalFacturaZ_B_Iva = 0.0;
		double totalFacturaZPositivosIva = 0.0;
		double totalFacturaZNegativosIva = 0.0;
		double totalFacturaZIva = 0.0;
		double totalFacturaAPositivosFacturaZ_A_PositivosIva = 0.0;
		double totalFacturaANegativosFacturaZ_A_NegativosIva = 0.0;
		double totalFacturaAFacturaZ_AIva = 0.0;
		double totalFacturaBPositivosFacturaZ_B_PositivosIva = 0.0;
		double totalFacturaBNegativosFacturaZ_B_NegativosIva = 0.0;
		double totalFacturaBFacturaZ_BIva = 0.0;
		double totalFacturaZ_A_Positivos_MonotributistasIva = 0.0;
		double totalFacturaZ_B_Positivos_MonotributistasIva = 0.0;
		double totalFacturaZ_B_Positivos_ConsumidoresFinalesIva = 0.0;
		double totalFacturaZ_A_Positivos_ExentosNoAlcanzadosIva = 0.0;
		double totalFacturaZ_B_Positivos_ExentosNoAlcanzadosIva = 0.0;
		double totalFacturaZ_A_Positivos_ResponsableInscriptoIva = 0.0;

		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next(); // Skip first header row
			rowIterator.next(); // Skip second header row

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Cell totalNetoGravadoCell = row.getCell(7);
				Cell letraCell = row.getCell(2);
				Cell numeroClienteCell = row.getCell(4);
				Cell razonSocialCell = row.getCell(3);
				Cell tipoDocumentoCell = row.getCell(1000);
				Cell totalIvaCell = row.getCell(8);
				Cell codigoIvaCell = row.getCell(5);
				Cell exentoCell = row.getCell(6);

				boolean requiereRevision = false;

				if (totalNetoGravadoCell != null) {
					double totalNetoGravado;
					double totalIva;

					try {

						if (totalNetoGravadoCell.getCellType() == CellType.NUMERIC) {
							totalNetoGravado = totalNetoGravadoCell.getNumericCellValue();
						} else if (totalNetoGravadoCell.getCellType() == CellType.STRING) {
							totalNetoGravado = Double
									.parseDouble(totalNetoGravadoCell.getStringCellValue().replace(",", ""));
						} else {
							System.out.println("La celda no contiene un valor numérico: " + totalNetoGravadoCell);
							continue;
						}
						if (totalIvaCell.getCellType() == CellType.NUMERIC) {
							totalIva = totalIvaCell.getNumericCellValue();
						} else if (totalIvaCell.getCellType() == CellType.STRING) {
							totalIva = Double.parseDouble(totalIvaCell.getStringCellValue().replace(",", ""));
						} else {
							System.out.println("La celda no contiene un valor numérico: " + totalIvaCell);
							continue;
						}

					} catch (NumberFormatException e) {
						System.out.println("Error al convertir el valor: " + totalNetoGravadoCell);
						continue;
					}

					String formattedTotalNetoGravado = String.format("%.2f", totalNetoGravado);
					String formattedTotalIva = String.format("%.2f", totalIva);

					if (letraCell != null && letraCell.getCellType() == CellType.STRING) {
						String letra = letraCell.getStringCellValue();
						String numeroCliente = numeroClienteCell != null
								&& numeroClienteCell.getCellType() == CellType.STRING
										? numeroClienteCell.getStringCellValue()
										: "";
						String razonSocial = razonSocialCell != null && razonSocialCell.getCellType() == CellType.STRING
								? razonSocialCell.getStringCellValue()
								: "";
						String tipoDocumento = tipoDocumentoCell != null
								&& tipoDocumentoCell.getCellType() == CellType.STRING
										? tipoDocumentoCell.getStringCellValue()
										: "";
						String codigoIva = codigoIvaCell != null && codigoIvaCell.getCellType() == CellType.STRING
								? codigoIvaCell.getStringCellValue()
								: "";
						String exento = exentoCell != null && exentoCell.getCellType() == CellType.STRING
								? exentoCell.getStringCellValue()
								: "";

						if (totalNetoGravado < 0) {
							System.out.println(" Nota de Crédito: " + formattedTotalNetoGravado.replace(".", ",")
									+ " - IVA: " + formattedTotalIva.replace(".", ","));
						} else {
							System.out.println("Factura Positiva: " + formattedTotalNetoGravado.replace(".", ",")
									+ " - IVA: " + formattedTotalIva.replace(".", ","));
						}

						if (letra.startsWith("Fc.A") || letra.startsWith("Nc.A")) {
							if (codigoIva.equalsIgnoreCase("R.Mon")) {
								System.out.println("Factura " + letra + " - Monotributista: "
										+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
										+ formattedTotalIva.replace(".", ",") + "\n");
								if (totalNetoGravado > 0) {
									totalFacturaAMonotributistaPositivos += totalNetoGravado;
									totalFacturaAMonotributistaPositivosIva += totalIva;

									totalFacturaAPositivosFacturaZ_A_Positivos += totalNetoGravado;
									totalFacturaAPositivosFacturaZ_A_PositivosIva += totalIva;

								} else if (totalNetoGravado < 0) {
									totalFacturaAMonotributistaNegativos += totalNetoGravado;
									totalFacturaAMonotributistaNegativosIva += totalIva;

									totalFacturaANegativosFacturaZ_A_Negativos += totalNetoGravado;
									totalFacturaANegativosFacturaZ_A_NegativosIva += totalIva;

								}
								totalFacturaAMonotributista += totalNetoGravado;
								totalFacturaAMonotributistaIva += totalIva;

							} else if (codigoIva.equalsIgnoreCase("R.Ins")) {
								System.out.println("Factura " + letra + " - Responsable Inscripto (RI): "
										+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
										+ formattedTotalIva.replace(".", ",") + "\n");
								if (totalNetoGravado > 0) {
									totalFacturaAResponsableInscriptoPositivos += totalNetoGravado;
									totalFacturaAResponsableInscriptoPositivosIva += totalIva;

									totalFacturaAPositivosFacturaZ_A_Positivos += totalNetoGravado;
									totalFacturaAPositivosFacturaZ_A_PositivosIva += totalIva;

								} else if (totalNetoGravado < 0) {
									totalFacturaAResponsableInscriptoNegativos += totalNetoGravado;
									totalFacturaAResponsableInscriptoNegativosIva += totalIva;

									totalFacturaANegativosFacturaZ_A_Negativos += totalNetoGravado;
									totalFacturaANegativosFacturaZ_A_NegativosIva += totalIva;

								}
								totalFacturaAResponsableInscripto += totalNetoGravado;
								totalFacturaAResponsableInscriptoIva += totalIva;

							} else if (codigoIva.equalsIgnoreCase("R.Exe") || exento.startsWith("0") != false) {
								System.out.println("Factura " + letra + " - Exentos, no Alcanzados, no Gravados: "
										+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
										+ formattedTotalIva.replace(".", ",") + "\n");
								if (totalNetoGravado > 0) {
									totalFacturaAExentosNoAlcanzadosPositivos += totalNetoGravado;
									totalFacturaAExentosNoAlcanzadosPositivosIva += totalIva;

									totalFacturaAPositivosFacturaZ_A_Positivos += totalNetoGravado;
									totalFacturaAPositivosFacturaZ_A_PositivosIva += totalIva;

								} else if (totalNetoGravado < 0) {
									totalFacturaAExentosNoAlcanzadosNegativos += totalNetoGravado;
									totalFacturaAExentosNoAlcanzadosNegativosIva += totalIva;

									totalFacturaANegativosFacturaZ_A_Negativos += totalNetoGravado;
									totalFacturaANegativosFacturaZ_A_NegativosIva += totalIva;

								}
								totalFacturaAExentosNoAlcanzados += totalNetoGravado;
								totalFacturaAExentosNoAlcanzadosIva += totalIva;

							}
							if (totalNetoGravado > 0) {
								totalFacturaAPositivos += totalNetoGravado;
								totalFacturaAPositivosIva += totalIva;

							} else if (totalNetoGravado < 0) {
								totalFacturaANegativos += totalNetoGravado;
								totalFacturaANegativosIva += totalIva;

							}
							totalFacturaA += totalNetoGravado;
							totalFacturaAIva += totalIva;

							totalFacturaAFacturaZ_A += totalNetoGravado;
							totalFacturaAFacturaZ_AIva += totalIva;

						} else if (letra.startsWith("Fc.B") || letra.startsWith("Nc.B")) {
							if (codigoIva.equalsIgnoreCase("R.Mon")) {
								System.out.println("Factura " + letra + " - Monotributista: "
										+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
										+ formattedTotalIva.replace(".", ",") + "\n");
								if (totalNetoGravado > 0) {
									totalFacturaBMonotributistaPositivos += totalNetoGravado;
									totalFacturaBMonotributistaPositivosIva += totalIva;

									totalFacturaBPositivosFacturaZ_B_Positivos += totalNetoGravado;
									totalFacturaBPositivosFacturaZ_B_PositivosIva += totalIva;

								} else if (totalNetoGravado < 0) {
									totalFacturaBMonotributistaNegativos += totalNetoGravado;
									totalFacturaBMonotributistaNegativosIva += totalIva;

									totalFacturaBNegativosFacturaZ_B_Negativos += totalNetoGravado;
									totalFacturaBNegativosFacturaZ_B_NegativosIva += totalIva;

								}
								totalFacturaBMonotributista += totalNetoGravado;
								totalFacturaBMonotributistaIva += totalIva;

							} else if (codigoIva.equalsIgnoreCase("R.Exe") || exento.startsWith("0") != false) {
								System.out.println("Factura " + letra + " - Exentos, no Alcanzados, no Gravados: "
										+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
										+ formattedTotalIva.replace(".", ",") + "\n");
								if (totalNetoGravado > 0) {
									totalFacturaBExentosNoAlcanzadosPositivos += totalNetoGravado;
									totalFacturaBExentosNoAlcanzadosPositivosIva += totalIva;

									totalFacturaBPositivosFacturaZ_B_Positivos += totalNetoGravado;
									totalFacturaBPositivosFacturaZ_B_PositivosIva += totalIva;

								} else if (totalNetoGravado < 0) {
									totalFacturaBExentosNoAlcanzadosNegativos += totalNetoGravado;
									totalFacturaBExentosNoAlcanzadosNegativosIva += totalIva;

									totalFacturaBNegativosFacturaZ_B_Negativos += totalNetoGravado;
									totalFacturaBNegativosFacturaZ_B_NegativosIva += totalIva;

								}
								totalFacturaBExentosNoAlcanzados += totalNetoGravado;
								totalFacturaBExentosNoAlcanzadosIva += totalIva;

							} else if (razonSocial.equalsIgnoreCase("Consumidor Final")
									|| codigoIva.equalsIgnoreCase("C.Fin")) {
								System.out.println("Factura " + letra + " - Consumidor Final: "
										+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
										+ formattedTotalIva.replace(".", ",") + "\n");
								if (totalNetoGravado > 0) {
									totalFacturaBConsumidorFinalPositivos += totalNetoGravado;
									totalFacturaBConsumidorFinalPositivosIva += totalIva;

									totalFacturaBPositivosFacturaZ_B_Positivos += totalNetoGravado;
									totalFacturaBPositivosFacturaZ_B_PositivosIva += totalIva;

								} else if (totalNetoGravado < 0) {
									totalFacturaBConsumidorFinalNegativos += totalNetoGravado;
									totalFacturaBConsumidorFinalNegativosIva += totalIva;

									totalFacturaBNegativosFacturaZ_B_Negativos += totalNetoGravado;
									totalFacturaBNegativosFacturaZ_B_NegativosIva += totalIva;

								}
								totalFacturaBConsumidorFinal += totalNetoGravado;
								totalFacturaBConsumidorFinalIva += totalIva;

							}
							if (totalNetoGravado > 0) {
								totalFacturaBPositivos += totalNetoGravado;
								totalFacturaBPositivosIva += totalIva;

							} else if (totalNetoGravado < 0) {
								totalFacturaBNegativos += totalNetoGravado;
								totalFacturaBNegativosIva += totalIva;

							}
							totalFacturaB += totalNetoGravado;
							totalFacturaBIva += totalIva;

							totalFacturaBFacturaZ_B += totalNetoGravado;
							totalFacturaBFacturaZ_BIva += totalIva;

						} else if (letra.startsWith("Fc.Z") || letra.startsWith("Nc.Z")) {

							totalFacturaZ += totalNetoGravado;
							totalFacturaZIva += totalIva;

							if (totalNetoGravado > 0) {
								totalFacturaZPositivos += totalNetoGravado;
								totalFacturaZPositivosIva += totalIva;

							} else if (totalNetoGravado < 0) {
								totalFacturaZNegativos += totalNetoGravado;
								totalFacturaZNegativosIva += totalIva;

							}
							if (codigoIva.equalsIgnoreCase("R.Mon")) {
								System.out.println("Factura " + letra + " - Monotributista: "
										+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
										+ formattedTotalIva.replace(".", ",") + "\n");
								totalFacturaZ_A += totalNetoGravado;
								totalFacturaZ_A_Iva += totalIva;

								if (totalNetoGravado > 0) {
									totalFacturaZ_A_Positivos += totalNetoGravado;
									totalFacturaZ_A_PositivosIva += totalIva;

									totalFacturaAPositivosFacturaZ_A_Positivos += totalNetoGravado;
									totalFacturaAPositivosFacturaZ_A_PositivosIva += totalIva;

									totalFacturaZ_A_Positivos_Monotributistas += totalNetoGravado;
									totalFacturaZ_A_Positivos_MonotributistasIva += totalIva;

								} else if (totalNetoGravado < 0) {
									totalFacturaZ_A_Negativos += totalNetoGravado;
									totalFacturaZ_A_NegativosIva += totalIva;

									totalFacturaANegativosFacturaZ_A_Negativos += totalNetoGravado;
									totalFacturaANegativosFacturaZ_A_NegativosIva += totalIva;

								}
								totalFacturaAFacturaZ_A += totalNetoGravado;
								totalFacturaAFacturaZ_AIva += totalIva;

							} else if (codigoIva.equalsIgnoreCase("R.Ins")) {
								System.out.println("Factura " + letra + " - Responsable Inscripto (RI): "
										+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
										+ formattedTotalIva.replace(".", ",") + "\n");
								totalFacturaZ_A += totalNetoGravado;
								totalFacturaZ_A_Iva += totalIva;

								if (totalNetoGravado > 0) {
									totalFacturaZ_A_Positivos += totalNetoGravado;
									totalFacturaZ_A_PositivosIva += totalIva;

									totalFacturaAPositivosFacturaZ_A_Positivos += totalNetoGravado;
									totalFacturaAPositivosFacturaZ_A_PositivosIva += totalIva;

								} else if (totalNetoGravado < 0) {
									totalFacturaZ_A_Negativos += totalNetoGravado;
									totalFacturaZ_A_NegativosIva += totalIva;

									totalFacturaANegativosFacturaZ_A_Negativos += totalNetoGravado;
									totalFacturaANegativosFacturaZ_A_NegativosIva += totalIva;

								}
								totalFacturaAFacturaZ_A += totalNetoGravado;
								totalFacturaAFacturaZ_AIva += totalIva;

							} else if (razonSocial.equalsIgnoreCase("Consumidor Final")
									|| codigoIva.equalsIgnoreCase("C.Fin")) {
								System.out.println("Factura " + letra + " - Consumidor Final: "
										+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
										+ formattedTotalIva.replace(".", ",") + "\n");
								totalFacturaZ_B += totalNetoGravado;
								totalFacturaZ_B_Iva += totalIva;

								if (totalNetoGravado > 0) {
									totalFacturaZ_B_Positivos += totalNetoGravado;
									totalFacturaZ_B_PositivosIva += totalIva;

									totalFacturaBPositivosFacturaZ_B_Positivos += totalNetoGravado;
									totalFacturaBPositivosFacturaZ_B_PositivosIva += totalIva;

									totalFacturaZ_B_Positivos_ConsumidoresFinales += totalNetoGravado;
									totalFacturaZ_B_Positivos_ConsumidoresFinalesIva += totalIva;

								} else if (totalNetoGravado < 0) {
									totalFacturaZ_B_Negativos += totalNetoGravado;
									totalFacturaZ_B_NegativosIva += totalIva;

									totalFacturaBNegativosFacturaZ_B_Negativos += totalNetoGravado;
									totalFacturaBNegativosFacturaZ_B_NegativosIva += totalIva;

								}
								totalFacturaBFacturaZ_B += totalNetoGravado;
								totalFacturaBFacturaZ_BIva += totalIva;

							} else {
								System.out.println("Factura " + letra + " - Exentos, no Alcanzados, no Gravados: "
										+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
										+ formattedTotalIva.replace(".", ",") + "\n");
								totalFacturaZ_B += totalNetoGravado;
								totalFacturaZ_B_Iva += totalIva;

								if (totalNetoGravado > 0) {
									totalFacturaZ_B_Positivos += totalNetoGravado;
									totalFacturaZ_B_PositivosIva += totalIva;

									totalFacturaBPositivosFacturaZ_B_Positivos += totalNetoGravado;
									totalFacturaBPositivosFacturaZ_B_PositivosIva += totalIva;

									totalFacturaZ_B_Positivos_ExentosNoAlcanzados += totalNetoGravado;
									totalFacturaZ_B_Positivos_ExentosNoAlcanzadosIva += totalIva;

								} else if (totalNetoGravado < 0) {
									totalFacturaZ_B_Negativos += totalNetoGravado;
									totalFacturaZ_B_NegativosIva += totalIva;

									totalFacturaBNegativosFacturaZ_B_Negativos += totalNetoGravado;
									totalFacturaBNegativosFacturaZ_B_NegativosIva += totalIva;

								}
								totalFacturaBFacturaZ_B += totalNetoGravado;
								totalFacturaBFacturaZ_BIva += totalIva;

							}

						} else {
							System.out.println("Factura " + letra + " - No clasificada: "
									+ formattedTotalNetoGravado.replace(".", ","));
							requiereRevision = true;
						}
						if (requiereRevision) {
							System.out.println("Requiere revisión: " + letra + " - " + razonSocial + " - "
									+ tipoDocumento + " - " + numeroCliente);
						}
					}

				}
			}

			// Formatear los resultados

			String formattedTotalFacturaAResponsableInscriptoPositivos = String
					.format("%.2f", totalFacturaAResponsableInscriptoPositivos).replace(".", ",");
			String formattedTotalFacturaAResponsableInscriptoNegativos = String
					.format("%.2f", totalFacturaAResponsableInscriptoNegativos).replace(".", ",");
			String formattedTotalFacturaAResponsableInscripto = String.format("%.2f", totalFacturaAResponsableInscripto)
					.replace(".", ",");
			String formattedTotalFacturaAMonotributistaPositivos = String
					.format("%.2f", totalFacturaAMonotributistaPositivos).replace(".", ",");
			String formattedTotalFacturaAMonotributistaNegativos = String
					.format("%.2f", totalFacturaAMonotributistaNegativos).replace(".", ",");
			String formattedTotalFacturaAMonotributista = String.format("%.2f", totalFacturaAMonotributista)
					.replace(".", ",");
			String formattedTotalFacturaAExentosNoAlcanzadosPositivos = String
					.format("%.2f", totalFacturaAExentosNoAlcanzadosPositivos).replace(".", ",");
			String formattedTotalFacturaAExentosNoAlcanzadosNegativos = String
					.format("%.2f", totalFacturaAExentosNoAlcanzadosNegativos).replace(".", ",");
			String formattedTotalFacturaAExentosNoAlcanzados = String.format("%.2f", totalFacturaAExentosNoAlcanzados)
					.replace(".", ",");
			String formattedTotalFacturaAPositivos = String.format("%.2f", totalFacturaAPositivos).replace(".", ",");
			String formattedTotalFacturaANegativos = String.format("%.2f", totalFacturaANegativos).replace(".", ",");
			String formattedTotalFacturaA = String.format("%.2f", totalFacturaA).replace(".", ",");

			String formattedTotalFacturaBConsumidorFinalPositivos = String
					.format("%.2f", totalFacturaBConsumidorFinalPositivos).replace(".", ",");
			String formattedTotalFacturaBConsumidorFinalNegativos = String
					.format("%.2f", totalFacturaBConsumidorFinalNegativos).replace(".", ",");
			String formattedTotalFacturaBConsumidorFinal = String.format("%.2f", totalFacturaBConsumidorFinal)
					.replace(".", ",");
			String formattedTotalFacturaBMonotributistaPositivos = String
					.format("%.2f", totalFacturaBMonotributistaPositivos).replace(".", ",");
			String formattedTotalFacturaBMonotributistaNegativos = String
					.format("%.2f", totalFacturaBMonotributistaNegativos).replace(".", ",");
			String formattedTotalFacturaBMonotributista = String.format("%.2f", totalFacturaBMonotributista)
					.replace(".", ",");
			String formattedTotalFacturaBExentosNoAlcanzadosPositivos = String
					.format("%.2f", totalFacturaBExentosNoAlcanzadosPositivos).replace(".", ",");
			String formattedTotalFacturaBExentosNoAlcanzadosNegativos = String
					.format("%.2f", totalFacturaBExentosNoAlcanzadosNegativos).replace(".", ",");
			String formattedTotalFacturaBExentosNoAlcanzados = String.format("%.2f", totalFacturaBExentosNoAlcanzados)
					.replace(".", ",");
			String formattedTotalFacturaBPositivos = String.format("%.2f", totalFacturaBPositivos).replace(".", ",");
			String formattedTotalFacturaBNegativos = String.format("%.2f", totalFacturaBNegativos).replace(".", ",");
			String formattedTotalFacturaB = String.format("%.2f", totalFacturaB).replace(".", ",");

			String formattedTotalFacturaZ_A_Positivos = String.format("%.2f", totalFacturaZ_A_Positivos).replace(".",
					",");
			String formattedTotalFacturaZ_A_Negativos = String.format("%.2f", totalFacturaZ_A_Negativos).replace(".",
					",");
			String formattedTotalFacturaZ_A = String.format("%.2f", totalFacturaZ_A).replace(".", ",");
			String formattedTotalFacturaZ_B_Positivos = String.format("%.2f", totalFacturaZ_B_Positivos).replace(".",
					",");
			String formattedTotalFacturaZ_B_Negativos = String.format("%.2f", totalFacturaZ_B_Negativos).replace(".",
					",");
			String formattedTotalFacturaZ_B = String.format("%.2f", totalFacturaZ_B).replace(".", ",");
			String formattedTotalFacturaZPositivos = String.format("%.2f", totalFacturaZPositivos).replace(".", ",");
			String formattedTotalFacturaZNegativos = String.format("%.2f", totalFacturaZNegativos).replace(".", ",");
			String formattedTotalFacturaZ = String.format("%.2f", totalFacturaZ).replace(".", ",");

			String formattedTotalFacturaAPositivosFacturaZ_A_Positivos = String
					.format("%.2f", totalFacturaAPositivosFacturaZ_A_Positivos).replace(".", ",");
			String formattedTotalFacturaANegativosFacturaZ_A_Negativos = String
					.format("%.2f", totalFacturaANegativosFacturaZ_A_Negativos).replace(".", ",");
			String formattedTotalFacturaAFacturaZ_A = String.format("%.2f", totalFacturaAFacturaZ_A).replace(".", ",");
			String formattedTotalFacturaBPositivosFacturaZ_B_Positivos = String
					.format("%.2f", totalFacturaBPositivosFacturaZ_B_Positivos).replace(".", ",");
			String formattedTotalFacturaBNegativosFacturaZ_B_Negativos = String
					.format("%.2f", totalFacturaBNegativosFacturaZ_B_Negativos).replace(".", ",");
			String formattedTotalFacturaBFacturaZ_B = String.format("%.2f", totalFacturaBFacturaZ_B).replace(".", ",");

			String formattedTotalFacturaZ_A_Positivos_Monotributistas = String
					.format("%.2f", totalFacturaZ_A_Positivos_Monotributistas).replace(".", ",");
			String formattedTotalFacturaZ_B_Positivos_Monotributistas = String
					.format("%.2f", totalFacturaZ_B_Positivos_Monotributistas).replace(".", ",");
			String formattedTotalFacturaZ_B_Positivos_ConsumidoresFinales = String
					.format("%.2f", totalFacturaZ_B_Positivos_ConsumidoresFinales).replace(".", ",");
			String formattedTotalFacturaZ_A_Positivos_ExentosNoAlcanzados = String
					.format("%.2f", totalFacturaZ_A_Positivos_ExentosNoAlcanzados).replace(".", ",");
			String formattedTotalFacturaZ_B_Positivos_ExentosNoAlcanzados = String
					.format("%.2f", totalFacturaZ_B_Positivos_ExentosNoAlcanzados).replace(".", ",");
			String formattedTotalFacturaZ_A_Positivos_ResponsableInscripto = String
					.format("%.2f", totalFacturaZ_A_Positivos_ResponsableInscripto).replace(".", ",");

			String formattedOpRi = String
					.format("%.2f",
							totalFacturaAResponsableInscriptoPositivos + totalFacturaZ_A_Positivos_ResponsableInscripto)
					.replace(".", ",");
			String formattedOpCfExentos = String.format("%.2f",
					totalFacturaZ_B_Positivos_ConsumidoresFinales + totalFacturaZ_A_Positivos_ExentosNoAlcanzados
							+ totalFacturaZ_B_Positivos_ExentosNoAlcanzados + totalFacturaAExentosNoAlcanzadosPositivos
							+ totalFacturaBConsumidorFinalPositivos + totalFacturaBExentosNoAlcanzadosPositivos)
					.replace(".", ",");
			String formattedOpMon = String
					.format("%.2f",
							totalFacturaZ_A_Positivos_Monotributistas + totalFacturaZ_B_Positivos_Monotributistas
									+ totalFacturaAMonotributistaPositivos + totalFacturaBMonotributistaPositivos)
					.replace(".", ",");
			String formattedtotalFacturaZAB_ABPositivos = String
					.format("%.2f",
							totalFacturaAPositivosFacturaZ_A_Positivos + totalFacturaBPositivosFacturaZ_B_Positivos)
					.replace(".", ",");
			String formattedtotalFacturaZAB_ABNegativos = String
					.format("%.2f",
							totalFacturaANegativosFacturaZ_A_Negativos + totalFacturaBNegativosFacturaZ_B_Negativos)
					.replace(".", ",");
			String formattedtotalFacturaZAB_AB = String
					.format("%.2f", totalFacturaAFacturaZ_A + totalFacturaBFacturaZ_B).replace(".", ",");

			String formattedTotalFacturaAResponsableInscriptoPositivosIva = String
					.format("%.2f", totalFacturaAResponsableInscriptoPositivosIva).replace(".", ",");
			String formattedTotalFacturaAResponsableInscriptoNegativosIva = String
					.format("%.2f", totalFacturaAResponsableInscriptoNegativosIva).replace(".", ",");
			String formattedTotalFacturaAResponsableInscriptoIva = String
					.format("%.2f", totalFacturaAResponsableInscriptoIva).replace(".", ",");
			String formattedTotalFacturaAMonotributistaPositivosIva = String
					.format("%.2f", totalFacturaAMonotributistaPositivosIva).replace(".", ",");
			String formattedTotalFacturaAMonotributistaNegativosIva = String
					.format("%.2f", totalFacturaAMonotributistaNegativosIva).replace(".", ",");
			String formattedTotalFacturaAMonotributistaIva = String.format("%.2f", totalFacturaAMonotributistaIva)
					.replace(".", ",");
			String formattedTotalFacturaAExentosNoAlcanzadosPositivosIva = String
					.format("%.2f", totalFacturaAExentosNoAlcanzadosPositivosIva).replace(".", ",");
			String formattedTotalFacturaAExentosNoAlcanzadosNegativosIva = String
					.format("%.2f", totalFacturaAExentosNoAlcanzadosNegativosIva).replace(".", ",");
			String formattedTotalFacturaAExentosNoAlcanzadosIva = String
					.format("%.2f", totalFacturaAExentosNoAlcanzadosIva).replace(".", ",");
			String formattedTotalFacturaAPositivosIva = String.format("%.2f", totalFacturaAPositivosIva).replace(".",
					",");
			String formattedTotalFacturaANegativosIva = String.format("%.2f", totalFacturaANegativosIva).replace(".",
					",");
			String formattedTotalFacturaAIva = String.format("%.2f", totalFacturaAIva).replace(".", ",");

			String formattedTotalFacturaBConsumidorFinalPositivosIva = String
					.format("%.2f", totalFacturaBConsumidorFinalPositivosIva).replace(".", ",");
			String formattedTotalFacturaBConsumidorFinalNegativosIva = String
					.format("%.2f", totalFacturaBConsumidorFinalNegativosIva).replace(".", ",");
			String formattedTotalFacturaBConsumidorFinalIva = String.format("%.2f", totalFacturaBConsumidorFinalIva)
					.replace(".", ",");
			String formattedTotalFacturaBMonotributistaPositivosIva = String
					.format("%.2f", totalFacturaBMonotributistaPositivosIva).replace(".", ",");
			String formattedTotalFacturaBMonotributistaNegativosIva = String
					.format("%.2f", totalFacturaBMonotributistaNegativosIva).replace(".", ",");
			String formattedTotalFacturaBMonotributistaIva = String.format("%.2f", totalFacturaBMonotributistaIva)
					.replace(".", ",");
			String formattedTotalFacturaBExentosNoAlcanzadosPositivosIva = String
					.format("%.2f", totalFacturaBExentosNoAlcanzadosPositivosIva).replace(".", ",");
			String formattedTotalFacturaBExentosNoAlcanzadosNegativosIva = String
					.format("%.2f", totalFacturaBExentosNoAlcanzadosNegativosIva).replace(".", ",");
			String formattedTotalFacturaBExentosNoAlcanzadosIva = String
					.format("%.2f", totalFacturaBExentosNoAlcanzadosIva).replace(".", ",");
			String formattedTotalFacturaBPositivosIva = String.format("%.2f", totalFacturaBPositivosIva).replace(".",
					",");
			String formattedTotalFacturaBNegativosIva = String.format("%.2f", totalFacturaBNegativosIva).replace(".",
					",");
			String formattedTotalFacturaBIva = String.format("%.2f", totalFacturaBIva).replace(".", ",");

			String formattedTotalFacturaZ_A_PositivosIva = String.format("%.2f", totalFacturaZ_A_PositivosIva)
					.replace(".", ",");
			String formattedTotalFacturaZ_A_NegativosIva = String.format("%.2f", totalFacturaZ_A_NegativosIva)
					.replace(".", ",");
			String formattedTotalFacturaZ_AIva = String.format("%.2f", totalFacturaZ_A_Iva).replace(".", ",");
			String formattedTotalFacturaZ_B_PositivosIva = String.format("%.2f", totalFacturaZ_B_PositivosIva)
					.replace(".", ",");
			String formattedTotalFacturaZ_B_NegativosIva = String.format("%.2f", totalFacturaZ_B_NegativosIva)
					.replace(".", ",");
			String formattedTotalFacturaZ_BIva = String.format("%.2f", totalFacturaZ_B_Iva).replace(".", ",");
			String formattedTotalFacturaZPositivosIva = String.format("%.2f", totalFacturaZPositivosIva).replace(".",
					",");
			String formattedTotalFacturaZNegativosIva = String.format("%.2f", totalFacturaZNegativosIva).replace(".",
					",");
			String formattedTotalFacturaZIva = String.format("%.2f", totalFacturaZIva).replace(".", ",");

			String formattedTotalFacturaAPositivosFacturaZ_A_PositivosIva = String
					.format("%.2f", totalFacturaAPositivosFacturaZ_A_PositivosIva).replace(".", ",");
			String formattedTotalFacturaANegativosFacturaZ_A_NegativosIva = String
					.format("%.2f", totalFacturaANegativosFacturaZ_A_NegativosIva).replace(".", ",");
			String formattedTotalFacturaAFacturaZ_AIva = String.format("%.2f", totalFacturaAFacturaZ_AIva).replace(".",
					",");
			String formattedTotalFacturaBPositivosFacturaZ_B_PositivosIva = String
					.format("%.2f", totalFacturaBPositivosFacturaZ_B_PositivosIva).replace(".", ",");
			String formattedTotalFacturaBNegativosFacturaZ_B_NegativosIva = String
					.format("%.2f", totalFacturaBNegativosFacturaZ_B_NegativosIva).replace(".", ",");
			String formattedTotalFacturaBFacturaZ_BIva = String.format("%.2f", totalFacturaBFacturaZ_BIva).replace(".",
					",");

			String formattedTotalFacturaZ_A_Positivos_MonotributistasIva = String
					.format("%.2f", totalFacturaZ_A_Positivos_MonotributistasIva).replace(".", ",");
			String formattedTotalFacturaZ_B_Positivos_MonotributistasIva = String
					.format("%.2f", totalFacturaZ_B_Positivos_MonotributistasIva).replace(".", ",");
			String formattedTotalFacturaZ_B_Positivos_ConsumidoresFinalesIva = String
					.format("%.2f", totalFacturaZ_B_Positivos_ConsumidoresFinalesIva).replace(".", ",");
			String formattedTotalFacturaZ_A_Positivos_ExentosNoAlcanzadosIva = String
					.format("%.2f", totalFacturaZ_A_Positivos_ExentosNoAlcanzadosIva).replace(".", ",");
			String formattedTotalFacturaZ_B_Positivos_ExentosNoAlcanzadosIva = String
					.format("%.2f", totalFacturaZ_B_Positivos_ExentosNoAlcanzadosIva).replace(".", ",");
			String formattedTotalFacturaZ_A_Positivos_ResponsableInscriptoIva = String
					.format("%.2f", totalFacturaZ_A_Positivos_ResponsableInscriptoIva).replace(".", ",");

			String formattedOpRiIva = String.format("%.2f",
					totalFacturaAResponsableInscriptoPositivosIva + totalFacturaZ_A_Positivos_ResponsableInscriptoIva)
					.replace(".", ",");
			String formattedOpCfExentosIva = String.format("%.2f",
					totalFacturaZ_B_Positivos_ConsumidoresFinalesIva + totalFacturaZ_A_Positivos_ExentosNoAlcanzadosIva
							+ totalFacturaZ_B_Positivos_ExentosNoAlcanzadosIva
							+ totalFacturaAExentosNoAlcanzadosPositivosIva + totalFacturaBConsumidorFinalPositivosIva
							+ totalFacturaBExentosNoAlcanzadosPositivosIva)
					.replace(".", ",");
			String formattedOpMonIva = String
					.format("%.2f",
							totalFacturaZ_A_Positivos_MonotributistasIva + totalFacturaZ_B_Positivos_MonotributistasIva
									+ totalFacturaAMonotributistaPositivosIva + totalFacturaBMonotributistaPositivosIva)
					.replace(".", ",");
			String formattedtotalFacturaZAB_ABPositivosIva = String.format("%.2f",
					totalFacturaAPositivosFacturaZ_A_PositivosIva + totalFacturaBPositivosFacturaZ_B_PositivosIva)
					.replace(".", ",");
			String formattedtotalFacturaZAB_ABNegativosIva = String.format("%.2f",
					totalFacturaANegativosFacturaZ_A_NegativosIva + totalFacturaBNegativosFacturaZ_B_NegativosIva)
					.replace(".", ",");
			String formattedtotalFacturaZAB_ABIva = String
					.format("%.2f", totalFacturaAFacturaZ_AIva + totalFacturaBFacturaZ_BIva).replace(".", ",");

			// Mostrar los resultados formateados

			System.out.println("\nTotales acumulados Neto Gravado con IVA:");

			System.out.println("\nFacturas A");

			System.out.println("Total Factura A Responsable Inscripto (RI) Positivos: "
					+ formattedTotalFacturaAResponsableInscriptoPositivos + " \tIVA\t "
					+ formattedTotalFacturaAResponsableInscriptoPositivosIva);
			System.out.println("Total Factura A Responsable Inscripto (RI) Negativos: "
					+ formattedTotalFacturaAResponsableInscriptoNegativos + " \tIVA\t "
					+ formattedTotalFacturaAResponsableInscriptoNegativosIva);
			System.out
					.println("Total Factura A Responsable Inscripto (RI): " + formattedTotalFacturaAResponsableInscripto
							+ " \tIVA\t " + formattedTotalFacturaAResponsableInscriptoIva);
			System.out.println(
					"Total Factura A Monotributista Positivos: " + formattedTotalFacturaAMonotributistaPositivos
							+ " \tIVA\t " + formattedTotalFacturaAMonotributistaPositivosIva);
			System.out.println(
					"Total Factura A Monotributista Negativos: " + formattedTotalFacturaAMonotributistaNegativos
							+ " \tIVA\t " + formattedTotalFacturaAMonotributistaNegativosIva);
			System.out.println("Total Factura A Monotributista: " + formattedTotalFacturaAMonotributista + " \tIVA\t "
					+ formattedTotalFacturaAMonotributistaIva);
			System.out.println("Total Factura A Exentos No Alcanzados Positivos: "
					+ formattedTotalFacturaAExentosNoAlcanzadosPositivos + " \tIVA\t "
					+ formattedTotalFacturaAExentosNoAlcanzadosPositivosIva);
			System.out.println("Total Factura A Exentos No Alcanzados Negativos: "
					+ formattedTotalFacturaAExentosNoAlcanzadosNegativos + " \tIVA\t "
					+ formattedTotalFacturaAExentosNoAlcanzadosNegativosIva);
			System.out.println("Total Factura A Exentos No Alcanzados: " + formattedTotalFacturaAExentosNoAlcanzados
					+ " \tIVA\t " + formattedTotalFacturaAExentosNoAlcanzadosIva);
			System.out.println("Total Factura A Positivos (sin Facturas Z positivas): "
					+ formattedTotalFacturaAPositivos + " \tIVA\t " + formattedTotalFacturaAPositivosIva);
			System.out.println("Total Factura A Negativos (sin Facturas Z negativas): "
					+ formattedTotalFacturaANegativos + " \tIVA\t " + formattedTotalFacturaANegativosIva);
			System.out.println("Total Factura A (sin Facturas Z): " + formattedTotalFacturaA + " \tIVA\t "
					+ formattedTotalFacturaAIva);

			System.out.println("\nFacturas B");

			System.out.println(
					"Total Factura B Consumidor Final Positivos: " + formattedTotalFacturaBConsumidorFinalPositivos
							+ " \tIVA\t " + formattedTotalFacturaBConsumidorFinalPositivosIva);
			System.out.println(
					"Total Factura B Consumidor Final Negativos: " + formattedTotalFacturaBConsumidorFinalNegativos
							+ " \tIVA\t " + formattedTotalFacturaBConsumidorFinalNegativosIva);
			System.out.println("Total Factura B Consumidor Final: " + formattedTotalFacturaBConsumidorFinal
					+ " \tIVA\t " + formattedTotalFacturaBConsumidorFinalIva);
			System.out.println(
					"Total Factura B Monotributista Positivos: " + formattedTotalFacturaBMonotributistaPositivos
							+ " \tIVA\t " + formattedTotalFacturaBMonotributistaPositivosIva);
			System.out.println(
					"Total Factura B Monotributista Negativos: " + formattedTotalFacturaBMonotributistaNegativos
							+ " \tIVA\t " + formattedTotalFacturaBMonotributistaNegativosIva);
			System.out.println("Total Factura B Monotributista: " + formattedTotalFacturaBMonotributista + " \tIVA\t "
					+ formattedTotalFacturaBMonotributistaIva);
			System.out.println("Total Factura B Exentos No Alcanzados Positivos: "
					+ formattedTotalFacturaBExentosNoAlcanzadosPositivos + " \tIVA\t "
					+ formattedTotalFacturaBExentosNoAlcanzadosPositivosIva);
			System.out.println("Total Factura B Exentos No Alcanzados Negativos: "
					+ formattedTotalFacturaBExentosNoAlcanzadosNegativos + " \tIVA\t "
					+ formattedTotalFacturaBExentosNoAlcanzadosNegativosIva);
			System.out.println("Total Factura B Exentos No Alcanzados: " + formattedTotalFacturaBExentosNoAlcanzados
					+ " \tIVA\t " + formattedTotalFacturaBExentosNoAlcanzadosIva);
			System.out.println("Total Factura B Positivos (sin Facturas Z positivas): "
					+ formattedTotalFacturaBPositivos + " \tIVA\t " + formattedTotalFacturaBPositivosIva);
			System.out.println("Total Factura B Negativos (sin Facturas Z negativas): "
					+ formattedTotalFacturaBNegativos + " \tIVA\t " + formattedTotalFacturaBNegativosIva);
			System.out.println("Total Factura B (sin facturas Z): " + formattedTotalFacturaB + " \tIVA\t "
					+ formattedTotalFacturaBIva);

			System.out.println("\nFacturas Z");

			System.out.println("Total Factura Z Que Son Factura A Positivos: " + formattedTotalFacturaZ_A_Positivos
					+ " \tIVA\t " + formattedTotalFacturaZ_A_PositivosIva);
			System.out.println("Total Factura Z Que Son Factura A Negativos: " + formattedTotalFacturaZ_A_Negativos
					+ " \tIVA\t " + formattedTotalFacturaZ_A_NegativosIva);
			System.out.println("Total Factura Z Que Son Factura A: " + formattedTotalFacturaZ_A + " \tIVA\t "
					+ formattedTotalFacturaZ_AIva);
			System.out.println("Total Factura Z Que Son Factura B Positivos: " + formattedTotalFacturaZ_B_Positivos
					+ " \tIVA\t " + formattedTotalFacturaZ_B_PositivosIva);
			System.out.println("Total Factura Z Que Son Factura B Negativos: " + formattedTotalFacturaZ_B_Negativos
					+ " \tIVA\t " + formattedTotalFacturaZ_B_NegativosIva);
			System.out.println("Total Factura Z Que Son Factura B: " + formattedTotalFacturaZ_B + " \tIVA\t "
					+ formattedTotalFacturaZ_BIva);
			System.out.println("Total Factura Z Positivos: " + formattedTotalFacturaZPositivos + " \tIVA\t "
					+ formattedTotalFacturaZPositivosIva);
			System.out.println("Total Factura Z Negativos: " + formattedTotalFacturaZNegativos + " \tIVA\t "
					+ formattedTotalFacturaZNegativosIva);
			System.out.println("Total Factura Z: " + formattedTotalFacturaZ + " \tIVA\t " + formattedTotalFacturaZIva);

			System.out.println("Total Factura A Positivos (con Facturas Z que son A Positivos): "
					+ formattedTotalFacturaAPositivosFacturaZ_A_Positivos + " \tIVA\t "
					+ formattedTotalFacturaAPositivosFacturaZ_A_PositivosIva);
			System.out.println("Total Factura A Negativos (con Facturas Z que son A Negativos): "
					+ formattedTotalFacturaANegativosFacturaZ_A_Negativos + " \tIVA\t "
					+ formattedTotalFacturaANegativosFacturaZ_A_NegativosIva);
			System.out.println("Total Factura A (con Facturas Z que son A): " + formattedTotalFacturaAFacturaZ_A
					+ " \tIVA\t " + formattedTotalFacturaAFacturaZ_AIva);

			System.out.println("Total Factura B Positivos (con Facturas Z que son B Positivos): "
					+ formattedTotalFacturaBPositivosFacturaZ_B_Positivos + " \tIVA\t "
					+ formattedTotalFacturaBPositivosFacturaZ_B_PositivosIva);
			System.out.println("Total Factura B Negativos (con Facturas Z que son B Negativos): "
					+ formattedTotalFacturaBNegativosFacturaZ_B_Negativos + " \tIVA\t "
					+ formattedTotalFacturaBNegativosFacturaZ_B_NegativosIva);
			System.out.println("Total Factura B (con Facturas Z que son B): " + formattedTotalFacturaBFacturaZ_B
					+ " \tIVA\t " + formattedTotalFacturaBFacturaZ_BIva);

			System.out.println("Total Factura Z Que Son Factura A Positivos Responsable Inscripto : "
					+ formattedTotalFacturaZ_A_Positivos_ResponsableInscripto + " \tIVA\t "
					+ formattedTotalFacturaZ_A_Positivos_ResponsableInscriptoIva);
			System.out.println(
					"Operaciones con responsables Inscriptos): " + formattedOpRi + " \tIVA\t " + formattedOpRiIva);

			System.out.println("Total Factura Z Que Son Factura A Positivos Monotributistas: "
					+ formattedTotalFacturaZ_A_Positivos_Monotributistas + " \tIVA\t "
					+ formattedTotalFacturaZ_A_Positivos_MonotributistasIva);
			System.out.println("Total Factura Z Que Son Factura B Positivos Monotributistas: "
					+ formattedTotalFacturaZ_B_Positivos_Monotributistas + " \tIVA\t "
					+ formattedTotalFacturaZ_B_Positivos_MonotributistasIva);

			System.out.println("Total Factura Z Que son Factura B Positivos Consumidores Finales: "
					+ formattedTotalFacturaZ_B_Positivos_ConsumidoresFinales + " \tIVA\t "
					+ formattedTotalFacturaZ_B_Positivos_ConsumidoresFinalesIva);

			System.out.println("Total Factura Z Que Son Factura A Positivos Exentos No Alcanzados: "
					+ formattedTotalFacturaZ_A_Positivos_ExentosNoAlcanzados + " \tIVA\t "
					+ formattedTotalFacturaZ_A_Positivos_ExentosNoAlcanzadosIva);

			System.out.println("Total Factura Z Que son Factura B Positivos Exentos No Alcanzados: "
					+ formattedTotalFacturaZ_B_Positivos_ExentosNoAlcanzados + " \tIVA\t "
					+ formattedTotalFacturaZ_B_Positivos_ExentosNoAlcanzadosIva);

			System.out.println("Operaciones con CF y EXENTOS NO ALCANZADOS: " + formattedOpCfExentos + " \tIVA\t "
					+ formattedOpCfExentosIva);
			System.out.println("Operaciones con MON: " + formattedOpMon + " \tIVA\t " + formattedOpMonIva);

			System.out.println("Total Facturas A, B y Z (que son A y B) Positivos: "
					+ formattedtotalFacturaZAB_ABPositivos + " \tIVA\t " + formattedtotalFacturaZAB_ABPositivosIva);
			System.out.println("Total Facturas A, B y Z (que son A y B) Negativos: "
					+ formattedtotalFacturaZAB_ABNegativos + " \tIVA\t " + formattedtotalFacturaZAB_ABNegativosIva);
			System.out.println("Total Facturas A, B y Z (que son A y B): " + formattedtotalFacturaZAB_AB + " \tIVA\t "
					+ formattedtotalFacturaZAB_ABIva);

			System.out.println("Factura Procesada");
			Scanner lector = new Scanner(System.in);
			String n = lector.nextLine();
			lector.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void procesarArchivoPorPuntoDeVenta(String filePath) {
		// Nueva lógica para múltiples puntos de venta
		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next(); // Skip first header row
			rowIterator.next(); // Skip second header row

			Map<String, List<Row>> puntoDeVentaMap = new HashMap<>();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Cell letraCell = row.getCell(2);
				if (letraCell != null && letraCell.getCellType() == CellType.STRING) {
					String letra = letraCell.getStringCellValue();
					String puntoDeVenta = letra.substring(4, 8); // Extraer los primeros 4 dígitos
					puntoDeVentaMap.computeIfAbsent(puntoDeVenta, k -> new ArrayList<>()).add(row);
				}
			}

			for (String puntoDeVenta : puntoDeVentaMap.keySet()) {
				System.out.println(ANSI_PURPLE + "\nProcesando punto de venta: " + puntoDeVenta + "\n" + ANSI_RESET);

				// Declaraciones Total Neto Gravado
				double totalFacturaAResponsableInscriptoPositivos = 0.0;
				double totalFacturaAResponsableInscriptoNegativos = 0.0;
				double totalFacturaAResponsableInscripto = 0.0;
				double totalFacturaAExentosNoAlcanzadosPositivos = 0.0;
				double totalFacturaAExentosNoAlcanzadosNegativos = 0.0;
				double totalFacturaAExentosNoAlcanzados = 0.0;
				double totalFacturaAMonotributistaPositivos = 0.0;
				double totalFacturaAMonotributistaNegativos = 0.0;
				double totalFacturaAMonotributista = 0.0;
				double totalFacturaAPositivos = 0.0;
				double totalFacturaANegativos = 0.0;
				double totalFacturaA = 0.0;
				double totalFacturaBConsumidorFinalPositivos = 0.0;
				double totalFacturaBConsumidorFinalNegativos = 0.0;
				double totalFacturaBConsumidorFinal = 0.0;
				double totalFacturaBMonotributistaPositivos = 0.0;
				double totalFacturaBMonotributistaNegativos = 0.0;
				double totalFacturaBMonotributista = 0.0;
				double totalFacturaBExentosNoAlcanzadosPositivos = 0.0;
				double totalFacturaBExentosNoAlcanzadosNegativos = 0.0;
				double totalFacturaBExentosNoAlcanzados = 0.0;
				double totalFacturaBPositivos = 0.0;
				double totalFacturaBNegativos = 0.0;
				double totalFacturaB = 0.0;
				double totalFacturaZ_A_Positivos = 0.0;
				double totalFacturaZ_A_Negativos = 0.0;
				double totalFacturaZ_A = 0.0;
				double totalFacturaZ_B_Positivos = 0.0;
				double totalFacturaZ_B_Negativos = 0.0;
				double totalFacturaZ_B = 0.0;
				double totalFacturaZPositivos = 0.0;
				double totalFacturaZNegativos = 0.0;
				double totalFacturaZ = 0.0;
				double totalFacturaAPositivosFacturaZ_A_Positivos = 0.0;
				double totalFacturaANegativosFacturaZ_A_Negativos = 0.0;
				double totalFacturaAFacturaZ_A = 0.0;
				double totalFacturaBPositivosFacturaZ_B_Positivos = 0.0;
				double totalFacturaBNegativosFacturaZ_B_Negativos = 0.0;
				double totalFacturaBFacturaZ_B = 0.0;
				double totalFacturaZ_A_Positivos_Monotributistas = 0.0;
				double totalFacturaZ_B_Positivos_Monotributistas = 0.0;
				double totalFacturaZ_B_Positivos_ConsumidoresFinales = 0.0;
				double totalFacturaZ_A_Positivos_ExentosNoAlcanzados = 0.0;
				double totalFacturaZ_B_Positivos_ExentosNoAlcanzados = 0.0;
				double totalFacturaZ_A_Positivos_ResponsableInscripto = 0.0;

				// Declaraciones Total Iva
				double totalFacturaAResponsableInscriptoPositivosIva = 0.0;
				double totalFacturaAResponsableInscriptoNegativosIva = 0.0;
				double totalFacturaAResponsableInscriptoIva = 0.0;
				double totalFacturaAExentosNoAlcanzadosPositivosIva = 0.0;
				double totalFacturaAExentosNoAlcanzadosNegativosIva = 0.0;
				double totalFacturaAExentosNoAlcanzadosIva = 0.0;
				double totalFacturaAMonotributistaPositivosIva = 0.0;
				double totalFacturaAMonotributistaNegativosIva = 0.0;
				double totalFacturaAMonotributistaIva = 0.0;
				double totalFacturaAPositivosIva = 0.0;
				double totalFacturaANegativosIva = 0.0;
				double totalFacturaAIva = 0.0;
				double totalFacturaBConsumidorFinalPositivosIva = 0.0;
				double totalFacturaBConsumidorFinalNegativosIva = 0.0;
				double totalFacturaBConsumidorFinalIva = 0.0;
				double totalFacturaBMonotributistaPositivosIva = 0.0;
				double totalFacturaBMonotributistaNegativosIva = 0.0;
				double totalFacturaBMonotributistaIva = 0.0;
				double totalFacturaBExentosNoAlcanzadosPositivosIva = 0.0;
				double totalFacturaBExentosNoAlcanzadosNegativosIva = 0.0;
				double totalFacturaBExentosNoAlcanzadosIva = 0.0;
				double totalFacturaBPositivosIva = 0.0;
				double totalFacturaBNegativosIva = 0.0;
				double totalFacturaBIva = 0.0;
				double totalFacturaZ_A_PositivosIva = 0.0;
				double totalFacturaZ_A_NegativosIva = 0.0;
				double totalFacturaZ_A_Iva = 0.0;
				double totalFacturaZ_B_PositivosIva = 0.0;
				double totalFacturaZ_B_NegativosIva = 0.0;
				double totalFacturaZ_B_Iva = 0.0;
				double totalFacturaZPositivosIva = 0.0;
				double totalFacturaZNegativosIva = 0.0;
				double totalFacturaZIva = 0.0;
				double totalFacturaAPositivosFacturaZ_A_PositivosIva = 0.0;
				double totalFacturaANegativosFacturaZ_A_NegativosIva = 0.0;
				double totalFacturaAFacturaZ_AIva = 0.0;
				double totalFacturaBPositivosFacturaZ_B_PositivosIva = 0.0;
				double totalFacturaBNegativosFacturaZ_B_NegativosIva = 0.0;
				double totalFacturaBFacturaZ_BIva = 0.0;
				double totalFacturaZ_A_Positivos_MonotributistasIva = 0.0;
				double totalFacturaZ_B_Positivos_MonotributistasIva = 0.0;
				double totalFacturaZ_B_Positivos_ConsumidoresFinalesIva = 0.0;
				double totalFacturaZ_A_Positivos_ExentosNoAlcanzadosIva = 0.0;
				double totalFacturaZ_B_Positivos_ExentosNoAlcanzadosIva = 0.0;
				double totalFacturaZ_A_Positivos_ResponsableInscriptoIva = 0.0;

				List<Row> rows = puntoDeVentaMap.get(puntoDeVenta);
				for (Row row : rows) {
					// Lógica original aplicada a cada grupo de filas por punto de venta

					Cell totalNetoGravadoCell = row.getCell(7);
					Cell letraCell = row.getCell(2);
					Cell numeroClienteCell = row.getCell(4);
					Cell razonSocialCell = row.getCell(3);
					Cell tipoDocumentoCell = row.getCell(1000);
					Cell totalIvaCell = row.getCell(8);
					Cell codigoIvaCell = row.getCell(5);
					Cell exentoCell = row.getCell(6);

					boolean requiereRevision = false;

					if (totalNetoGravadoCell != null) {
						double totalNetoGravado;
						double totalIva;

						try {
							if (totalNetoGravadoCell.getCellType() == CellType.NUMERIC) {
								totalNetoGravado = totalNetoGravadoCell.getNumericCellValue();
							} else if (totalNetoGravadoCell.getCellType() == CellType.STRING) {
								totalNetoGravado = Double
										.parseDouble(totalNetoGravadoCell.getStringCellValue().replace(",", ""));
							} else {
								System.out.println("La celda no contiene un valor numérico: " + totalNetoGravadoCell);
								continue;
							}
							if (totalIvaCell.getCellType() == CellType.NUMERIC) {
								totalIva = totalIvaCell.getNumericCellValue();
							} else if (totalIvaCell.getCellType() == CellType.STRING) {
								totalIva = Double.parseDouble(totalIvaCell.getStringCellValue().replace(",", ""));
							} else {
								System.out.println("La celda no contiene un valor numérico: " + totalIvaCell);
								continue;
							}
						} catch (NumberFormatException e) {
							System.out.println("Error al convertir el valor: " + totalNetoGravadoCell);
							continue;
						}

						String formattedTotalNetoGravado = String.format("%.2f", totalNetoGravado);
						String formattedTotalIva = String.format("%.2f", totalIva);

						if (letraCell != null && letraCell.getCellType() == CellType.STRING) {
							String letra = letraCell.getStringCellValue();
							String numeroCliente = numeroClienteCell != null
									&& numeroClienteCell.getCellType() == CellType.STRING
											? numeroClienteCell.getStringCellValue()
											: "";
							String razonSocial = razonSocialCell != null
									&& razonSocialCell.getCellType() == CellType.STRING
											? razonSocialCell.getStringCellValue()
											: "";
							String tipoDocumento = tipoDocumentoCell != null
									&& tipoDocumentoCell.getCellType() == CellType.STRING
											? tipoDocumentoCell.getStringCellValue()
											: "";
							String codigoIva = codigoIvaCell != null && codigoIvaCell.getCellType() == CellType.STRING
									? codigoIvaCell.getStringCellValue()
									: "";
							String exento = exentoCell != null && exentoCell.getCellType() == CellType.STRING
									? exentoCell.getStringCellValue()
									: "";

							if (totalNetoGravado < 0) {
								System.out.println(" Nota de Crédito: " + formattedTotalNetoGravado.replace(".", ",")
										+ " - IVA: " + formattedTotalIva.replace(".", ","));
							} else {
								System.out.println("Factura Positiva: " + formattedTotalNetoGravado.replace(".", ",")
										+ " - IVA: " + formattedTotalIva.replace(".", ","));
							}

							// Aquí continúa la lógica de procesamiento...
							if (letra.startsWith("Fc.A") || letra.startsWith("Nc.A")) {
								if (codigoIva.equalsIgnoreCase("R.Mon")) {
									System.out.println("Factura " + letra + " - Monotributista: "
											+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
											+ formattedTotalIva.replace(".", ",") + "\n");
									if (totalNetoGravado > 0) {
										totalFacturaAMonotributistaPositivos += totalNetoGravado;
										totalFacturaAMonotributistaPositivosIva += totalIva;

										totalFacturaAPositivosFacturaZ_A_Positivos += totalNetoGravado;
										totalFacturaAPositivosFacturaZ_A_PositivosIva += totalIva;

									} else if (totalNetoGravado < 0) {
										totalFacturaAMonotributistaNegativos += totalNetoGravado;
										totalFacturaAMonotributistaNegativosIva += totalIva;

										totalFacturaANegativosFacturaZ_A_Negativos += totalNetoGravado;
										totalFacturaANegativosFacturaZ_A_NegativosIva += totalIva;

									}
									totalFacturaAMonotributista += totalNetoGravado;
									totalFacturaAMonotributistaIva += totalIva;

								} else if (codigoIva.equalsIgnoreCase("R.Ins")) {
									System.out.println("Factura " + letra + " - Responsable Inscripto (RI): "
											+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
											+ formattedTotalIva.replace(".", ",") + "\n");
									if (totalNetoGravado > 0) {
										totalFacturaAResponsableInscriptoPositivos += totalNetoGravado;
										totalFacturaAResponsableInscriptoPositivosIva += totalIva;

										totalFacturaAPositivosFacturaZ_A_Positivos += totalNetoGravado;
										totalFacturaAPositivosFacturaZ_A_PositivosIva += totalIva;

									} else if (totalNetoGravado < 0) {
										totalFacturaAResponsableInscriptoNegativos += totalNetoGravado;
										totalFacturaAResponsableInscriptoNegativosIva += totalIva;

										totalFacturaANegativosFacturaZ_A_Negativos += totalNetoGravado;
										totalFacturaANegativosFacturaZ_A_NegativosIva += totalIva;

									}
									totalFacturaAResponsableInscripto += totalNetoGravado;
									totalFacturaAResponsableInscriptoIva += totalIva;

								} else if (codigoIva.equalsIgnoreCase("R.Exe") || exento.startsWith("0") != false) {
									System.out.println("Factura " + letra + " - Exentos, no Alcanzados, no Gravados: "
											+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
											+ formattedTotalIva.replace(".", ",") + "\n");
									if (totalNetoGravado > 0) {
										totalFacturaAExentosNoAlcanzadosPositivos += totalNetoGravado;
										totalFacturaAExentosNoAlcanzadosPositivosIva += totalIva;

										totalFacturaAPositivosFacturaZ_A_Positivos += totalNetoGravado;
										totalFacturaAPositivosFacturaZ_A_PositivosIva += totalIva;

									} else if (totalNetoGravado < 0) {
										totalFacturaAExentosNoAlcanzadosNegativos += totalNetoGravado;
										totalFacturaAExentosNoAlcanzadosNegativosIva += totalIva;

										totalFacturaANegativosFacturaZ_A_Negativos += totalNetoGravado;
										totalFacturaANegativosFacturaZ_A_NegativosIva += totalIva;

									}
									totalFacturaAExentosNoAlcanzados += totalNetoGravado;
									totalFacturaAExentosNoAlcanzadosIva += totalIva;

								}
								if (totalNetoGravado > 0) {
									totalFacturaAPositivos += totalNetoGravado;
									totalFacturaAPositivosIva += totalIva;

								} else if (totalNetoGravado < 0) {
									totalFacturaANegativos += totalNetoGravado;
									totalFacturaANegativosIva += totalIva;

								}
								totalFacturaA += totalNetoGravado;
								totalFacturaAIva += totalIva;

								totalFacturaAFacturaZ_A += totalNetoGravado;
								totalFacturaAFacturaZ_AIva += totalIva;

							} else if (letra.startsWith("Fc.B") || letra.startsWith("Nc.B")) {
								if (codigoIva.equalsIgnoreCase("R.Mon")) {
									System.out.println("Factura " + letra + " - Monotributista: "
											+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
											+ formattedTotalIva.replace(".", ",") + "\n");
									if (totalNetoGravado > 0) {
										totalFacturaBMonotributistaPositivos += totalNetoGravado;
										totalFacturaBMonotributistaPositivosIva += totalIva;

										totalFacturaBPositivosFacturaZ_B_Positivos += totalNetoGravado;
										totalFacturaBPositivosFacturaZ_B_PositivosIva += totalIva;

									} else if (totalNetoGravado < 0) {
										totalFacturaBMonotributistaNegativos += totalNetoGravado;
										totalFacturaBMonotributistaNegativosIva += totalIva;

										totalFacturaBNegativosFacturaZ_B_Negativos += totalNetoGravado;
										totalFacturaBNegativosFacturaZ_B_NegativosIva += totalIva;

									}
									totalFacturaBMonotributista += totalNetoGravado;
									totalFacturaBMonotributistaIva += totalIva;

								} else if (codigoIva.equalsIgnoreCase("R.Exe") || exento.startsWith("0") != false) {
									System.out.println("Factura " + letra + " - Exentos, no Alcanzados, no Gravados: "
											+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
											+ formattedTotalIva.replace(".", ",") + "\n");
									if (totalNetoGravado > 0) {
										totalFacturaBExentosNoAlcanzadosPositivos += totalNetoGravado;
										totalFacturaBExentosNoAlcanzadosPositivosIva += totalIva;

										totalFacturaBPositivosFacturaZ_B_Positivos += totalNetoGravado;
										totalFacturaBPositivosFacturaZ_B_PositivosIva += totalIva;

									} else if (totalNetoGravado < 0) {
										totalFacturaBExentosNoAlcanzadosNegativos += totalNetoGravado;
										totalFacturaBExentosNoAlcanzadosNegativosIva += totalIva;

										totalFacturaBNegativosFacturaZ_B_Negativos += totalNetoGravado;
										totalFacturaBNegativosFacturaZ_B_NegativosIva += totalIva;

									}
									totalFacturaBExentosNoAlcanzados += totalNetoGravado;
									totalFacturaBExentosNoAlcanzadosIva += totalIva;

								} else if (razonSocial.equalsIgnoreCase("Consumidor Final")
										|| codigoIva.equalsIgnoreCase("C.Fin")) {
									System.out.println("Factura " + letra + " - Consumidor Final: "
											+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
											+ formattedTotalIva.replace(".", ",") + "\n");
									if (totalNetoGravado > 0) {
										totalFacturaBConsumidorFinalPositivos += totalNetoGravado;
										totalFacturaBConsumidorFinalPositivosIva += totalIva;

										totalFacturaBPositivosFacturaZ_B_Positivos += totalNetoGravado;
										totalFacturaBPositivosFacturaZ_B_PositivosIva += totalIva;

									} else if (totalNetoGravado < 0) {
										totalFacturaBConsumidorFinalNegativos += totalNetoGravado;
										totalFacturaBConsumidorFinalNegativosIva += totalIva;

										totalFacturaBNegativosFacturaZ_B_Negativos += totalNetoGravado;
										totalFacturaBNegativosFacturaZ_B_NegativosIva += totalIva;

									}
									totalFacturaBConsumidorFinal += totalNetoGravado;
									totalFacturaBConsumidorFinalIva += totalIva;

								}
								if (totalNetoGravado > 0) {
									totalFacturaBPositivos += totalNetoGravado;
									totalFacturaBPositivosIva += totalIva;

								} else if (totalNetoGravado < 0) {
									totalFacturaBNegativos += totalNetoGravado;
									totalFacturaBNegativosIva += totalIva;

								}
								totalFacturaB += totalNetoGravado;
								totalFacturaBIva += totalIva;

								totalFacturaBFacturaZ_B += totalNetoGravado;
								totalFacturaBFacturaZ_BIva += totalIva;

							} else if (letra.startsWith("Fc.Z") || letra.startsWith("Nc.Z")) {

								totalFacturaZ += totalNetoGravado;
								totalFacturaZIva += totalIva;

								if (totalNetoGravado > 0) {
									totalFacturaZPositivos += totalNetoGravado;
									totalFacturaZPositivosIva += totalIva;

								} else if (totalNetoGravado < 0) {
									totalFacturaZNegativos += totalNetoGravado;
									totalFacturaZNegativosIva += totalIva;

								}
								if (codigoIva.equalsIgnoreCase("R.Mon")) {
									System.out.println("Factura " + letra + " - Monotributista: "
											+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
											+ formattedTotalIva.replace(".", ",") + "\n");
									totalFacturaZ_A += totalNetoGravado;
									totalFacturaZ_A_Iva += totalIva;

									if (totalNetoGravado > 0) {
										totalFacturaZ_A_Positivos += totalNetoGravado;
										totalFacturaZ_A_PositivosIva += totalIva;

										totalFacturaAPositivosFacturaZ_A_Positivos += totalNetoGravado;
										totalFacturaAPositivosFacturaZ_A_PositivosIva += totalIva;

										totalFacturaZ_A_Positivos_Monotributistas += totalNetoGravado;
										totalFacturaZ_A_Positivos_MonotributistasIva += totalIva;

									} else if (totalNetoGravado < 0) {
										totalFacturaZ_A_Negativos += totalNetoGravado;
										totalFacturaZ_A_NegativosIva += totalIva;

										totalFacturaANegativosFacturaZ_A_Negativos += totalNetoGravado;
										totalFacturaANegativosFacturaZ_A_NegativosIva += totalIva;

									}
									totalFacturaAFacturaZ_A += totalNetoGravado;
									totalFacturaAFacturaZ_AIva += totalIva;

								} else if (codigoIva.equalsIgnoreCase("R.Ins")) {
									System.out.println("Factura " + letra + " - Responsable Inscripto (RI): "
											+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
											+ formattedTotalIva.replace(".", ",") + "\n");
									totalFacturaZ_A += totalNetoGravado;
									totalFacturaZ_A_Iva += totalIva;

									if (totalNetoGravado > 0) {
										totalFacturaZ_A_Positivos += totalNetoGravado;
										totalFacturaZ_A_PositivosIva += totalIva;

										totalFacturaAPositivosFacturaZ_A_Positivos += totalNetoGravado;
										totalFacturaAPositivosFacturaZ_A_PositivosIva += totalIva;

									} else if (totalNetoGravado < 0) {
										totalFacturaZ_A_Negativos += totalNetoGravado;
										totalFacturaZ_A_NegativosIva += totalIva;

										totalFacturaANegativosFacturaZ_A_Negativos += totalNetoGravado;
										totalFacturaANegativosFacturaZ_A_NegativosIva += totalIva;

									}
									totalFacturaAFacturaZ_A += totalNetoGravado;
									totalFacturaAFacturaZ_AIva += totalIva;

								} else if (razonSocial.equalsIgnoreCase("Consumidor Final")
										|| codigoIva.equalsIgnoreCase("C.Fin")) {
									System.out.println("Factura " + letra + " - Consumidor Final: "
											+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
											+ formattedTotalIva.replace(".", ",") + "\n");
									totalFacturaZ_B += totalNetoGravado;
									totalFacturaZ_B_Iva += totalIva;

									if (totalNetoGravado > 0) {
										totalFacturaZ_B_Positivos += totalNetoGravado;
										totalFacturaZ_B_PositivosIva += totalIva;

										totalFacturaBPositivosFacturaZ_B_Positivos += totalNetoGravado;
										totalFacturaBPositivosFacturaZ_B_PositivosIva += totalIva;

										totalFacturaZ_B_Positivos_ConsumidoresFinales += totalNetoGravado;
										totalFacturaZ_B_Positivos_ConsumidoresFinalesIva += totalIva;

									} else if (totalNetoGravado < 0) {
										totalFacturaZ_B_Negativos += totalNetoGravado;
										totalFacturaZ_B_NegativosIva += totalIva;

										totalFacturaBNegativosFacturaZ_B_Negativos += totalNetoGravado;
										totalFacturaBNegativosFacturaZ_B_NegativosIva += totalIva;

									}
									totalFacturaBFacturaZ_B += totalNetoGravado;
									totalFacturaBFacturaZ_BIva += totalIva;

								} else {
									System.out.println("Factura " + letra + " - Exentos, no Alcanzados, no Gravados: "
											+ formattedTotalNetoGravado.replace(".", ",") + " - IVA: "
											+ formattedTotalIva.replace(".", ",") + "\n");
									totalFacturaZ_B += totalNetoGravado;
									totalFacturaZ_B_Iva += totalIva;

									if (totalNetoGravado > 0) {
										totalFacturaZ_B_Positivos += totalNetoGravado;
										totalFacturaZ_B_PositivosIva += totalIva;

										totalFacturaBPositivosFacturaZ_B_Positivos += totalNetoGravado;
										totalFacturaBPositivosFacturaZ_B_PositivosIva += totalIva;

										totalFacturaZ_B_Positivos_ExentosNoAlcanzados += totalNetoGravado;
										totalFacturaZ_B_Positivos_ExentosNoAlcanzadosIva += totalIva;

									} else if (totalNetoGravado < 0) {
										totalFacturaZ_B_Negativos += totalNetoGravado;
										totalFacturaZ_B_NegativosIva += totalIva;

										totalFacturaBNegativosFacturaZ_B_Negativos += totalNetoGravado;
										totalFacturaBNegativosFacturaZ_B_NegativosIva += totalIva;

									}
									totalFacturaBFacturaZ_B += totalNetoGravado;
									totalFacturaBFacturaZ_BIva += totalIva;

								}

							} else {
								System.out.println("Factura " + letra + " - No clasificada: "
										+ formattedTotalNetoGravado.replace(".", ","));
								requiereRevision = true;
							}
							if (requiereRevision) {
								System.out.println("Requiere revisión: " + letra + " - " + razonSocial + " - "
										+ tipoDocumento + " - " + numeroCliente);
							}
						}

					}
				}

				// Formatear los resultados

				String formattedTotalFacturaAResponsableInscriptoPositivos = String
						.format("%.2f", totalFacturaAResponsableInscriptoPositivos).replace(".", ",");
				String formattedTotalFacturaAResponsableInscriptoNegativos = String
						.format("%.2f", totalFacturaAResponsableInscriptoNegativos).replace(".", ",");
				String formattedTotalFacturaAResponsableInscripto = String
						.format("%.2f", totalFacturaAResponsableInscripto).replace(".", ",");
				String formattedTotalFacturaAMonotributistaPositivos = String
						.format("%.2f", totalFacturaAMonotributistaPositivos).replace(".", ",");
				String formattedTotalFacturaAMonotributistaNegativos = String
						.format("%.2f", totalFacturaAMonotributistaNegativos).replace(".", ",");
				String formattedTotalFacturaAMonotributista = String.format("%.2f", totalFacturaAMonotributista)
						.replace(".", ",");
				String formattedTotalFacturaAExentosNoAlcanzadosPositivos = String
						.format("%.2f", totalFacturaAExentosNoAlcanzadosPositivos).replace(".", ",");
				String formattedTotalFacturaAExentosNoAlcanzadosNegativos = String
						.format("%.2f", totalFacturaAExentosNoAlcanzadosNegativos).replace(".", ",");
				String formattedTotalFacturaAExentosNoAlcanzados = String
						.format("%.2f", totalFacturaAExentosNoAlcanzados).replace(".", ",");
				String formattedTotalFacturaAPositivos = String.format("%.2f", totalFacturaAPositivos).replace(".",
						",");
				String formattedTotalFacturaANegativos = String.format("%.2f", totalFacturaANegativos).replace(".",
						",");
				String formattedTotalFacturaA = String.format("%.2f", totalFacturaA).replace(".", ",");

				String formattedTotalFacturaBConsumidorFinalPositivos = String
						.format("%.2f", totalFacturaBConsumidorFinalPositivos).replace(".", ",");
				String formattedTotalFacturaBConsumidorFinalNegativos = String
						.format("%.2f", totalFacturaBConsumidorFinalNegativos).replace(".", ",");
				String formattedTotalFacturaBConsumidorFinal = String.format("%.2f", totalFacturaBConsumidorFinal)
						.replace(".", ",");
				String formattedTotalFacturaBMonotributistaPositivos = String
						.format("%.2f", totalFacturaBMonotributistaPositivos).replace(".", ",");
				String formattedTotalFacturaBMonotributistaNegativos = String
						.format("%.2f", totalFacturaBMonotributistaNegativos).replace(".", ",");
				String formattedTotalFacturaBMonotributista = String.format("%.2f", totalFacturaBMonotributista)
						.replace(".", ",");
				String formattedTotalFacturaBExentosNoAlcanzadosPositivos = String
						.format("%.2f", totalFacturaBExentosNoAlcanzadosPositivos).replace(".", ",");
				String formattedTotalFacturaBExentosNoAlcanzadosNegativos = String
						.format("%.2f", totalFacturaBExentosNoAlcanzadosNegativos).replace(".", ",");
				String formattedTotalFacturaBExentosNoAlcanzados = String
						.format("%.2f", totalFacturaBExentosNoAlcanzados).replace(".", ",");
				String formattedTotalFacturaBPositivos = String.format("%.2f", totalFacturaBPositivos).replace(".",
						",");
				String formattedTotalFacturaBNegativos = String.format("%.2f", totalFacturaBNegativos).replace(".",
						",");
				String formattedTotalFacturaB = String.format("%.2f", totalFacturaB).replace(".", ",");

				String formattedTotalFacturaZ_A_Positivos = String.format("%.2f", totalFacturaZ_A_Positivos)
						.replace(".", ",");
				String formattedTotalFacturaZ_A_Negativos = String.format("%.2f", totalFacturaZ_A_Negativos)
						.replace(".", ",");
				String formattedTotalFacturaZ_A = String.format("%.2f", totalFacturaZ_A).replace(".", ",");
				String formattedTotalFacturaZ_B_Positivos = String.format("%.2f", totalFacturaZ_B_Positivos)
						.replace(".", ",");
				String formattedTotalFacturaZ_B_Negativos = String.format("%.2f", totalFacturaZ_B_Negativos)
						.replace(".", ",");
				String formattedTotalFacturaZ_B = String.format("%.2f", totalFacturaZ_B).replace(".", ",");
				String formattedTotalFacturaZPositivos = String.format("%.2f", totalFacturaZPositivos).replace(".",
						",");
				String formattedTotalFacturaZNegativos = String.format("%.2f", totalFacturaZNegativos).replace(".",
						",");
				String formattedTotalFacturaZ = String.format("%.2f", totalFacturaZ).replace(".", ",");

				String formattedTotalFacturaAPositivosFacturaZ_A_Positivos = String
						.format("%.2f", totalFacturaAPositivosFacturaZ_A_Positivos).replace(".", ",");
				String formattedTotalFacturaANegativosFacturaZ_A_Negativos = String
						.format("%.2f", totalFacturaANegativosFacturaZ_A_Negativos).replace(".", ",");
				String formattedTotalFacturaAFacturaZ_A = String.format("%.2f", totalFacturaAFacturaZ_A).replace(".",
						",");
				String formattedTotalFacturaBPositivosFacturaZ_B_Positivos = String
						.format("%.2f", totalFacturaBPositivosFacturaZ_B_Positivos).replace(".", ",");
				String formattedTotalFacturaBNegativosFacturaZ_B_Negativos = String
						.format("%.2f", totalFacturaBNegativosFacturaZ_B_Negativos).replace(".", ",");
				String formattedTotalFacturaBFacturaZ_B = String.format("%.2f", totalFacturaBFacturaZ_B).replace(".",
						",");

				String formattedTotalFacturaZ_A_Positivos_Monotributistas = String
						.format("%.2f", totalFacturaZ_A_Positivos_Monotributistas).replace(".", ",");
				String formattedTotalFacturaZ_B_Positivos_Monotributistas = String
						.format("%.2f", totalFacturaZ_B_Positivos_Monotributistas).replace(".", ",");
				String formattedTotalFacturaZ_B_Positivos_ConsumidoresFinales = String
						.format("%.2f", totalFacturaZ_B_Positivos_ConsumidoresFinales).replace(".", ",");
				String formattedTotalFacturaZ_A_Positivos_ExentosNoAlcanzados = String
						.format("%.2f", totalFacturaZ_A_Positivos_ExentosNoAlcanzados).replace(".", ",");
				String formattedTotalFacturaZ_B_Positivos_ExentosNoAlcanzados = String
						.format("%.2f", totalFacturaZ_B_Positivos_ExentosNoAlcanzados).replace(".", ",");
				String formattedTotalFacturaZ_A_Positivos_ResponsableInscripto = String
						.format("%.2f", totalFacturaZ_A_Positivos_ResponsableInscripto).replace(".", ",");

				String formattedOpRi = String.format("%.2f",
						totalFacturaAResponsableInscriptoPositivos + totalFacturaZ_A_Positivos_ResponsableInscripto)
						.replace(".", ",");
				String formattedOpCfExentos = String.format("%.2f",
						totalFacturaZ_B_Positivos_ConsumidoresFinales + totalFacturaZ_A_Positivos_ExentosNoAlcanzados
								+ totalFacturaZ_B_Positivos_ExentosNoAlcanzados
								+ totalFacturaAExentosNoAlcanzadosPositivos + totalFacturaBConsumidorFinalPositivos
								+ totalFacturaBExentosNoAlcanzadosPositivos)
						.replace(".", ",");
				String formattedOpMon = String
						.format("%.2f",
								totalFacturaZ_A_Positivos_Monotributistas + totalFacturaZ_B_Positivos_Monotributistas
										+ totalFacturaAMonotributistaPositivos + totalFacturaBMonotributistaPositivos)
						.replace(".", ",");
				String formattedtotalFacturaZAB_ABPositivos = String
						.format("%.2f",
								totalFacturaAPositivosFacturaZ_A_Positivos + totalFacturaBPositivosFacturaZ_B_Positivos)
						.replace(".", ",");
				String formattedtotalFacturaZAB_ABNegativos = String
						.format("%.2f",
								totalFacturaANegativosFacturaZ_A_Negativos + totalFacturaBNegativosFacturaZ_B_Negativos)
						.replace(".", ",");
				String formattedtotalFacturaZAB_AB = String
						.format("%.2f", totalFacturaAFacturaZ_A + totalFacturaBFacturaZ_B).replace(".", ",");

				String formattedTotalFacturaAResponsableInscriptoPositivosIva = String
						.format("%.2f", totalFacturaAResponsableInscriptoPositivosIva).replace(".", ",");
				String formattedTotalFacturaAResponsableInscriptoNegativosIva = String
						.format("%.2f", totalFacturaAResponsableInscriptoNegativosIva).replace(".", ",");
				String formattedTotalFacturaAResponsableInscriptoIva = String
						.format("%.2f", totalFacturaAResponsableInscriptoIva).replace(".", ",");
				String formattedTotalFacturaAMonotributistaPositivosIva = String
						.format("%.2f", totalFacturaAMonotributistaPositivosIva).replace(".", ",");
				String formattedTotalFacturaAMonotributistaNegativosIva = String
						.format("%.2f", totalFacturaAMonotributistaNegativosIva).replace(".", ",");
				String formattedTotalFacturaAMonotributistaIva = String.format("%.2f", totalFacturaAMonotributistaIva)
						.replace(".", ",");
				String formattedTotalFacturaAExentosNoAlcanzadosPositivosIva = String
						.format("%.2f", totalFacturaAExentosNoAlcanzadosPositivosIva).replace(".", ",");
				String formattedTotalFacturaAExentosNoAlcanzadosNegativosIva = String
						.format("%.2f", totalFacturaAExentosNoAlcanzadosNegativosIva).replace(".", ",");
				String formattedTotalFacturaAExentosNoAlcanzadosIva = String
						.format("%.2f", totalFacturaAExentosNoAlcanzadosIva).replace(".", ",");
				String formattedTotalFacturaAPositivosIva = String.format("%.2f", totalFacturaAPositivosIva)
						.replace(".", ",");
				String formattedTotalFacturaANegativosIva = String.format("%.2f", totalFacturaANegativosIva)
						.replace(".", ",");
				String formattedTotalFacturaAIva = String.format("%.2f", totalFacturaAIva).replace(".", ",");

				String formattedTotalFacturaBConsumidorFinalPositivosIva = String
						.format("%.2f", totalFacturaBConsumidorFinalPositivosIva).replace(".", ",");
				String formattedTotalFacturaBConsumidorFinalNegativosIva = String
						.format("%.2f", totalFacturaBConsumidorFinalNegativosIva).replace(".", ",");
				String formattedTotalFacturaBConsumidorFinalIva = String.format("%.2f", totalFacturaBConsumidorFinalIva)
						.replace(".", ",");
				String formattedTotalFacturaBMonotributistaPositivosIva = String
						.format("%.2f", totalFacturaBMonotributistaPositivosIva).replace(".", ",");
				String formattedTotalFacturaBMonotributistaNegativosIva = String
						.format("%.2f", totalFacturaBMonotributistaNegativosIva).replace(".", ",");
				String formattedTotalFacturaBMonotributistaIva = String.format("%.2f", totalFacturaBMonotributistaIva)
						.replace(".", ",");
				String formattedTotalFacturaBExentosNoAlcanzadosPositivosIva = String
						.format("%.2f", totalFacturaBExentosNoAlcanzadosPositivosIva).replace(".", ",");
				String formattedTotalFacturaBExentosNoAlcanzadosNegativosIva = String
						.format("%.2f", totalFacturaBExentosNoAlcanzadosNegativosIva).replace(".", ",");
				String formattedTotalFacturaBExentosNoAlcanzadosIva = String
						.format("%.2f", totalFacturaBExentosNoAlcanzadosIva).replace(".", ",");
				String formattedTotalFacturaBPositivosIva = String.format("%.2f", totalFacturaBPositivosIva)
						.replace(".", ",");
				String formattedTotalFacturaBNegativosIva = String.format("%.2f", totalFacturaBNegativosIva)
						.replace(".", ",");
				String formattedTotalFacturaBIva = String.format("%.2f", totalFacturaBIva).replace(".", ",");

				String formattedTotalFacturaZ_A_PositivosIva = String.format("%.2f", totalFacturaZ_A_PositivosIva)
						.replace(".", ",");
				String formattedTotalFacturaZ_A_NegativosIva = String.format("%.2f", totalFacturaZ_A_NegativosIva)
						.replace(".", ",");
				String formattedTotalFacturaZ_AIva = String.format("%.2f", totalFacturaZ_A_Iva).replace(".", ",");
				String formattedTotalFacturaZ_B_PositivosIva = String.format("%.2f", totalFacturaZ_B_PositivosIva)
						.replace(".", ",");
				String formattedTotalFacturaZ_B_NegativosIva = String.format("%.2f", totalFacturaZ_B_NegativosIva)
						.replace(".", ",");
				String formattedTotalFacturaZ_BIva = String.format("%.2f", totalFacturaZ_B_Iva).replace(".", ",");
				String formattedTotalFacturaZPositivosIva = String.format("%.2f", totalFacturaZPositivosIva)
						.replace(".", ",");
				String formattedTotalFacturaZNegativosIva = String.format("%.2f", totalFacturaZNegativosIva)
						.replace(".", ",");
				String formattedTotalFacturaZIva = String.format("%.2f", totalFacturaZIva).replace(".", ",");

				String formattedTotalFacturaAPositivosFacturaZ_A_PositivosIva = String
						.format("%.2f", totalFacturaAPositivosFacturaZ_A_PositivosIva).replace(".", ",");
				String formattedTotalFacturaANegativosFacturaZ_A_NegativosIva = String
						.format("%.2f", totalFacturaANegativosFacturaZ_A_NegativosIva).replace(".", ",");
				String formattedTotalFacturaAFacturaZ_AIva = String.format("%.2f", totalFacturaAFacturaZ_AIva)
						.replace(".", ",");
				String formattedTotalFacturaBPositivosFacturaZ_B_PositivosIva = String
						.format("%.2f", totalFacturaBPositivosFacturaZ_B_PositivosIva).replace(".", ",");
				String formattedTotalFacturaBNegativosFacturaZ_B_NegativosIva = String
						.format("%.2f", totalFacturaBNegativosFacturaZ_B_NegativosIva).replace(".", ",");
				String formattedTotalFacturaBFacturaZ_BIva = String.format("%.2f", totalFacturaBFacturaZ_BIva)
						.replace(".", ",");

				String formattedTotalFacturaZ_A_Positivos_MonotributistasIva = String
						.format("%.2f", totalFacturaZ_A_Positivos_MonotributistasIva).replace(".", ",");
				String formattedTotalFacturaZ_B_Positivos_MonotributistasIva = String
						.format("%.2f", totalFacturaZ_B_Positivos_MonotributistasIva).replace(".", ",");
				String formattedTotalFacturaZ_B_Positivos_ConsumidoresFinalesIva = String
						.format("%.2f", totalFacturaZ_B_Positivos_ConsumidoresFinalesIva).replace(".", ",");
				String formattedTotalFacturaZ_A_Positivos_ExentosNoAlcanzadosIva = String
						.format("%.2f", totalFacturaZ_A_Positivos_ExentosNoAlcanzadosIva).replace(".", ",");
				String formattedTotalFacturaZ_B_Positivos_ExentosNoAlcanzadosIva = String
						.format("%.2f", totalFacturaZ_B_Positivos_ExentosNoAlcanzadosIva).replace(".", ",");
				String formattedTotalFacturaZ_A_Positivos_ResponsableInscriptoIva = String
						.format("%.2f", totalFacturaZ_A_Positivos_ResponsableInscriptoIva).replace(".", ",");

				String formattedOpRiIva = String.format("%.2f", totalFacturaAResponsableInscriptoPositivosIva
						+ totalFacturaZ_A_Positivos_ResponsableInscriptoIva).replace(".", ",");
				String formattedOpCfExentosIva = String.format("%.2f", totalFacturaZ_B_Positivos_ConsumidoresFinalesIva
						+ totalFacturaZ_A_Positivos_ExentosNoAlcanzadosIva
						+ totalFacturaZ_B_Positivos_ExentosNoAlcanzadosIva
						+ totalFacturaAExentosNoAlcanzadosPositivosIva + totalFacturaBConsumidorFinalPositivosIva
						+ totalFacturaBExentosNoAlcanzadosPositivosIva).replace(".", ",");
				String formattedOpMonIva = String.format("%.2f",
						totalFacturaZ_A_Positivos_MonotributistasIva + totalFacturaZ_B_Positivos_MonotributistasIva
								+ totalFacturaAMonotributistaPositivosIva + totalFacturaBMonotributistaPositivosIva)
						.replace(".", ",");
				String formattedtotalFacturaZAB_ABPositivosIva = String.format("%.2f",
						totalFacturaAPositivosFacturaZ_A_PositivosIva + totalFacturaBPositivosFacturaZ_B_PositivosIva)
						.replace(".", ",");
				String formattedtotalFacturaZAB_ABNegativosIva = String.format("%.2f",
						totalFacturaANegativosFacturaZ_A_NegativosIva + totalFacturaBNegativosFacturaZ_B_NegativosIva)
						.replace(".", ",");
				String formattedtotalFacturaZAB_ABIva = String
						.format("%.2f", totalFacturaAFacturaZ_AIva + totalFacturaBFacturaZ_BIva).replace(".", ",");

				// Mostrar los resultados formateados

				System.out.println(ANSI_YELLOW + "\nPunto de venta: " + puntoDeVenta + ANSI_RESET);

				System.out.println("\nTotales acumulados Neto Gravado con IVA:");

				System.out.println("\nFacturas A");

				System.out.println("Total Factura A Responsable Inscripto (RI) Positivos: "
						+ formattedTotalFacturaAResponsableInscriptoPositivos + " \tIVA\t "
						+ formattedTotalFacturaAResponsableInscriptoPositivosIva);
				System.out.println("Total Factura A Responsable Inscripto (RI) Negativos: "
						+ formattedTotalFacturaAResponsableInscriptoNegativos + " \tIVA\t "
						+ formattedTotalFacturaAResponsableInscriptoNegativosIva);
				System.out.println(
						"Total Factura A Responsable Inscripto (RI): " + formattedTotalFacturaAResponsableInscripto
								+ " \tIVA\t " + formattedTotalFacturaAResponsableInscriptoIva);
				System.out.println(
						"Total Factura A Monotributista Positivos: " + formattedTotalFacturaAMonotributistaPositivos
								+ " \tIVA\t " + formattedTotalFacturaAMonotributistaPositivosIva);
				System.out.println(
						"Total Factura A Monotributista Negativos: " + formattedTotalFacturaAMonotributistaNegativos
								+ " \tIVA\t " + formattedTotalFacturaAMonotributistaNegativosIva);
				System.out.println("Total Factura A Monotributista: " + formattedTotalFacturaAMonotributista
						+ " \tIVA\t " + formattedTotalFacturaAMonotributistaIva);
				System.out.println("Total Factura A Exentos No Alcanzados Positivos: "
						+ formattedTotalFacturaAExentosNoAlcanzadosPositivos + " \tIVA\t "
						+ formattedTotalFacturaAExentosNoAlcanzadosPositivosIva);
				System.out.println("Total Factura A Exentos No Alcanzados Negativos: "
						+ formattedTotalFacturaAExentosNoAlcanzadosNegativos + " \tIVA\t "
						+ formattedTotalFacturaAExentosNoAlcanzadosNegativosIva);
				System.out.println("Total Factura A Exentos No Alcanzados: " + formattedTotalFacturaAExentosNoAlcanzados
						+ " \tIVA\t " + formattedTotalFacturaAExentosNoAlcanzadosIva);
				System.out.println("Total Factura A Positivos (sin Facturas Z positivas): "
						+ formattedTotalFacturaAPositivos + " \tIVA\t " + formattedTotalFacturaAPositivosIva);
				System.out.println("Total Factura A Negativos (sin Facturas Z negativas): "
						+ formattedTotalFacturaANegativos + " \tIVA\t " + formattedTotalFacturaANegativosIva);
				System.out.println("Total Factura A (sin Facturas Z): " + formattedTotalFacturaA + " \tIVA\t "
						+ formattedTotalFacturaAIva);

				System.out.println("\nFacturas B");

				System.out.println(
						"Total Factura B Consumidor Final Positivos: " + formattedTotalFacturaBConsumidorFinalPositivos
								+ " \tIVA\t " + formattedTotalFacturaBConsumidorFinalPositivosIva);
				System.out.println(
						"Total Factura B Consumidor Final Negativos: " + formattedTotalFacturaBConsumidorFinalNegativos
								+ " \tIVA\t " + formattedTotalFacturaBConsumidorFinalNegativosIva);
				System.out.println("Total Factura B Consumidor Final: " + formattedTotalFacturaBConsumidorFinal
						+ " \tIVA\t " + formattedTotalFacturaBConsumidorFinalIva);
				System.out.println(
						"Total Factura B Monotributista Positivos: " + formattedTotalFacturaBMonotributistaPositivos
								+ " \tIVA\t " + formattedTotalFacturaBMonotributistaPositivosIva);
				System.out.println(
						"Total Factura B Monotributista Negativos: " + formattedTotalFacturaBMonotributistaNegativos
								+ " \tIVA\t " + formattedTotalFacturaBMonotributistaNegativosIva);
				System.out.println("Total Factura B Monotributista: " + formattedTotalFacturaBMonotributista
						+ " \tIVA\t " + formattedTotalFacturaBMonotributistaIva);
				System.out.println("Total Factura B Exentos No Alcanzados Positivos: "
						+ formattedTotalFacturaBExentosNoAlcanzadosPositivos + " \tIVA\t "
						+ formattedTotalFacturaBExentosNoAlcanzadosPositivosIva);
				System.out.println("Total Factura B Exentos No Alcanzados Negativos: "
						+ formattedTotalFacturaBExentosNoAlcanzadosNegativos + " \tIVA\t "
						+ formattedTotalFacturaBExentosNoAlcanzadosNegativosIva);
				System.out.println("Total Factura B Exentos No Alcanzados: " + formattedTotalFacturaBExentosNoAlcanzados
						+ " \tIVA\t " + formattedTotalFacturaBExentosNoAlcanzadosIva);
				System.out.println("Total Factura B Positivos (sin Facturas Z positivas): "
						+ formattedTotalFacturaBPositivos + " \tIVA\t " + formattedTotalFacturaBPositivosIva);
				System.out.println("Total Factura B Negativos (sin Facturas Z negativas): "
						+ formattedTotalFacturaBNegativos + " \tIVA\t " + formattedTotalFacturaBNegativosIva);
				System.out.println("Total Factura B (sin facturas Z): " + formattedTotalFacturaB + " \tIVA\t "
						+ formattedTotalFacturaBIva);

				System.out.println("\nFacturas Z");

				System.out.println("Total Factura Z Que Son Factura A Positivos: " + formattedTotalFacturaZ_A_Positivos
						+ " \tIVA\t " + formattedTotalFacturaZ_A_PositivosIva);
				System.out.println("Total Factura Z Que Son Factura A Negativos: " + formattedTotalFacturaZ_A_Negativos
						+ " \tIVA\t " + formattedTotalFacturaZ_A_NegativosIva);
				System.out.println("Total Factura Z Que Son Factura A: " + formattedTotalFacturaZ_A + " \tIVA\t "
						+ formattedTotalFacturaZ_AIva);
				System.out.println("Total Factura Z Que Son Factura B Positivos: " + formattedTotalFacturaZ_B_Positivos
						+ " \tIVA\t " + formattedTotalFacturaZ_B_PositivosIva);
				System.out.println("Total Factura Z Que Son Factura B Negativos: " + formattedTotalFacturaZ_B_Negativos
						+ " \tIVA\t " + formattedTotalFacturaZ_B_NegativosIva);
				System.out.println("Total Factura Z Que Son Factura B: " + formattedTotalFacturaZ_B + " \tIVA\t "
						+ formattedTotalFacturaZ_BIva);
				System.out.println("Total Factura Z Positivos: " + formattedTotalFacturaZPositivos + " \tIVA\t "
						+ formattedTotalFacturaZPositivosIva);
				System.out.println("Total Factura Z Negativos: " + formattedTotalFacturaZNegativos + " \tIVA\t "
						+ formattedTotalFacturaZNegativosIva);
				System.out.println(
						"Total Factura Z: " + formattedTotalFacturaZ + " \tIVA\t " + formattedTotalFacturaZIva);

				System.out.println("Total Factura A Positivos (con Facturas Z que son A Positivos): "
						+ formattedTotalFacturaAPositivosFacturaZ_A_Positivos + " \tIVA\t "
						+ formattedTotalFacturaAPositivosFacturaZ_A_PositivosIva);
				System.out.println("Total Factura A Negativos (con Facturas Z que son A Negativos): "
						+ formattedTotalFacturaANegativosFacturaZ_A_Negativos + " \tIVA\t "
						+ formattedTotalFacturaANegativosFacturaZ_A_NegativosIva);
				System.out.println("Total Factura A (con Facturas Z que son A): " + formattedTotalFacturaAFacturaZ_A
						+ " \tIVA\t " + formattedTotalFacturaAFacturaZ_AIva);

				System.out.println("Total Factura B Positivos (con Facturas Z que son B Positivos): "
						+ formattedTotalFacturaBPositivosFacturaZ_B_Positivos + " \tIVA\t "
						+ formattedTotalFacturaBPositivosFacturaZ_B_PositivosIva);
				System.out.println("Total Factura B Negativos (con Facturas Z que son B Negativos): "
						+ formattedTotalFacturaBNegativosFacturaZ_B_Negativos + " \tIVA\t "
						+ formattedTotalFacturaBNegativosFacturaZ_B_NegativosIva);
				System.out.println("Total Factura B (con Facturas Z que son B): " + formattedTotalFacturaBFacturaZ_B
						+ " \tIVA\t " + formattedTotalFacturaBFacturaZ_BIva);

				System.out.println("Total Factura Z Que Son Factura A Positivos Responsable Inscripto : "
						+ formattedTotalFacturaZ_A_Positivos_ResponsableInscripto + " \tIVA\t "
						+ formattedTotalFacturaZ_A_Positivos_ResponsableInscriptoIva);
				System.out.println(
						"Operaciones con responsables Inscriptos): " + formattedOpRi + " \tIVA\t " + formattedOpRiIva);

				System.out.println("Total Factura Z Que Son Factura A Positivos Monotributistas: "
						+ formattedTotalFacturaZ_A_Positivos_Monotributistas + " \tIVA\t "
						+ formattedTotalFacturaZ_A_Positivos_MonotributistasIva);
				System.out.println("Total Factura Z Que Son Factura B Positivos Monotributistas: "
						+ formattedTotalFacturaZ_B_Positivos_Monotributistas + " \tIVA\t "
						+ formattedTotalFacturaZ_B_Positivos_MonotributistasIva);

				System.out.println("Total Factura Z Que son Factura B Positivos Consumidores Finales: "
						+ formattedTotalFacturaZ_B_Positivos_ConsumidoresFinales + " \tIVA\t "
						+ formattedTotalFacturaZ_B_Positivos_ConsumidoresFinalesIva);

				System.out.println("Total Factura Z Que Son Factura A Positivos Exentos No Alcanzados: "
						+ formattedTotalFacturaZ_A_Positivos_ExentosNoAlcanzados + " \tIVA\t "
						+ formattedTotalFacturaZ_A_Positivos_ExentosNoAlcanzadosIva);

				System.out.println("Total Factura Z Que son Factura B Positivos Exentos No Alcanzados: "
						+ formattedTotalFacturaZ_B_Positivos_ExentosNoAlcanzados + " \tIVA\t "
						+ formattedTotalFacturaZ_B_Positivos_ExentosNoAlcanzadosIva);

				System.out.println("Operaciones con CF y EXENTOS NO ALCANZADOS: " + formattedOpCfExentos + " \tIVA\t "
						+ formattedOpCfExentosIva);
				System.out.println("Operaciones con MON: " + formattedOpMon + " \tIVA\t " + formattedOpMonIva);

				System.out.println("Total Facturas A, B y Z (que son A y B) Positivos: "
						+ formattedtotalFacturaZAB_ABPositivos + " \tIVA\t " + formattedtotalFacturaZAB_ABPositivosIva);
				System.out.println("Total Facturas A, B y Z (que son A y B) Negativos: "
						+ formattedtotalFacturaZAB_ABNegativos + " \tIVA\t " + formattedtotalFacturaZAB_ABNegativosIva);
				System.out.println("Total Facturas A, B y Z (que son A y B): " + formattedtotalFacturaZAB_AB
						+ " \tIVA\t " + formattedtotalFacturaZAB_ABIva);

				System.out.println("Factura Procesada");
			}

			Scanner lector = new Scanner(System.in);
			String n = lector.nextLine();
			lector.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/*
 * ACLARACIONES: (1) En este programa codigoIvaCell Tiene prioridad porque el
 * dato es correcto. Por lo que numeroCliente queda descartado de condiciones.
 * (2) Fc y Nc se evaluan siempre, para determinar cual es cual, ahi se
 * determinan los positivos y negativos.
 * 
 */
