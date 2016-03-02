package matoski.com.fyberdemooffers.handlers;

/**
 * Created by ilijamt on 3/2/16.
 */
public interface AsyncTaskResponseHandler<T> {
    public void onPostExecute(T response);
}
