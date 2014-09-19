package de.bwaldvogel.liblinear;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * <p>
 * Describes the problem
 * </p>
 * 
 * For example, if we have the following training data:
 * 
 * <pre>
 *  LABEL       ATTR1   ATTR2   ATTR3   ATTR4   ATTR5
 *  -----       -----   -----   -----   -----   -----
 *  1           0       0.1     0.2     0       0
 *  2           0       0.1     0.3    -1.2     0
 *  1           0.4     0       0       0       0
 *  2           0       0.1     0       1.4     0.5
 *  3          -0.1    -0.2     0.1     1.1     0.1
 * 
 *  and bias = 1, then the components of problem are:
 * 
 *  l = 5
 *  n = 6
 * 
 *  y -&gt; 1 2 1 2 3
 * 
 *  x -&gt; [ ] -&gt; (2,0.1) (3,0.2) (6,1) (-1,?)
 *       [ ] -&gt; (2,0.1) (3,0.3) (4,-1.2) (6,1) (-1,?)
 *       [ ] -&gt; (1,0.4) (6,1) (-1,?)
 *       [ ] -&gt; (2,0.1) (4,1.4) (5,0.5) (6,1) (-1,?)
 *       [ ] -&gt; (1,-0.1) (2,-0.2) (3,0.1) (4,1.1) (5,0.1) (6,1) (-1,?)
 * </pre>
 */
public class Problem {

	/** the number of training data */
	public int l;

	/** the number of features (including the bias feature if bias &gt;= 0) */
	public int n;

	/** an array containing the target values */
	public double[] y;

	/** array of sparse feature nodes */
	public Feature[][] x;

	/**
	 * If bias &gt;= 0, we assume that one additional feature is added to the end
	 * of each data instance
	 */
	public double bias;

	/**
	 * @author hy
	 * @date 10/13/13
	 * 
	 *       an array for storing instance weights
	 */
	public double[] W;

	// hy{
	public Problem(Problem prob) {
		l = prob.l;
		n = prob.n;
		y = Arrays.copyOf(prob.y, prob.y.length);
		x = new Feature[prob.x.length][];
		for (int i = 0; i < prob.x.length; i++)
			x[i] = Arrays.copyOf(prob.x[i], prob.x[i].length);
		bias = prob.bias;
		W = Arrays.copyOf(prob.W, prob.W.length);
	}

	public Problem() {

	}

	// }hy

	/**
	 * see {@link Train#readProblem(File, double)}
	 */
	// hy* public static Problem readFromFile(File file, double bias) throws
	// IOException, InvalidInputDataException {
	// hy* return Train.readProblem(file, bias);
	public static Problem readFromFile(File file, double bias,
			String weightFilename) throws IOException, InvalidInputDataException {
		return Train.readProblem(file, bias, weightFilename);
	}
}